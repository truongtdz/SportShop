package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.dto.request.AddressRequest;
import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.dto.response.UserResponse;
import com.sportshop.sportshop.entity.AddressEntity;
import com.sportshop.sportshop.entity.CartEntity;
import com.sportshop.sportshop.entity.OrderDetailEntity;
import com.sportshop.sportshop.entity.OrderEntity;
import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.enums.RoleEnum;
import com.sportshop.sportshop.enums.StatusEnum;
import com.sportshop.sportshop.enums.StatusOrderEnum;
import com.sportshop.sportshop.exception.AppException;
import com.sportshop.sportshop.exception.ErrorCode;
import com.sportshop.sportshop.mapper.UserMapper;
import com.sportshop.sportshop.repository.AddressRepository;
import com.sportshop.sportshop.repository.CartRepository;
import com.sportshop.sportshop.repository.OrderDetailRepository;
import com.sportshop.sportshop.repository.OrderRepository;
import com.sportshop.sportshop.repository.ProductRepository;
import com.sportshop.sportshop.repository.UserRepository;
import com.sportshop.sportshop.service.CloudinaryService;
import com.sportshop.sportshop.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    // View user quantity
    @Override
    public int countUsers() {
        return userRepository.findByStatus(StatusEnum.Active).size();
    }

    // Find By Username
    @Override
    public UserEntity getUserByUsername(String username) {
        if(!userRepository.existsByUsernameAndStatus(username, StatusEnum.Active)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return userRepository.findByUsernameAndStatus(username, StatusEnum.Active);
    }

    // View user by Id
    @Override
    public UserResponse getUserById(Long userId) {
        if(!userRepository.existsByIdAndStatus(userId, StatusEnum.Active)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        UserResponse userResponse =userMapper.toUserResponse(userRepository.findById(userId).get()) ;
        return userResponse ;
    }

    // View all users
    @Override
    public List<UserResponse> getUsers() {
        List<UserResponse> users = new ArrayList<>();
        for(UserEntity user: userRepository.findByStatus(StatusEnum.Active)){
            users.add(userMapper.toUserResponse(user));
        }
        return users;
    }

    // Register user
    @Override
    public UserEntity registerUser(CreateUserRequest request) {
        if(userRepository.existsByUsernameAndStatus(request.getUsername(), StatusEnum.Active)){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        UserEntity newUser = userMapper.toUserEntity(request);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setCreateDate(new Date());
        newUser.setRoles(RoleEnum.USER);
        newUser.setStatus(StatusEnum.Active);

        return userRepository.save(newUser);
    }


    // Create user
    @Override
    public UserEntity createUser(CreateUserRequest request, MultipartFile file) {
        if(userRepository.existsByUsernameAndStatus(request.getUsername(), StatusEnum.Active)){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        UserEntity newUser = userMapper.toUserEntity(request);

        if(!file.isEmpty() && file != null){
            try{
                newUser.setAvatar(cloudinaryService.uploadFile(file));
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setCreateDate(new Date());
        newUser.setRoles(RoleEnum.USER);
        newUser.setStatus(StatusEnum.Active);

        return userRepository.save(newUser);
    }

    // Update user
    @Override
    public UserResponse updateUser(UpdateUserRequest request, Long userId, MultipartFile file) {
        if(!userRepository.existsByIdAndStatus(userId, StatusEnum.Active)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        UserEntity updateUser = userRepository.findByIdAndStatus(userId, StatusEnum.Active);

        if(!file.isEmpty() && file != null){
            try{
                updateUser.setAvatar(cloudinaryService.uploadFile(file));
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if(request.getPassword() != null && !request.getPassword().isEmpty()){
            updateUser.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if(request.getPhone() != null && !request.getPhone().isEmpty()){
            updateUser.setPhone(request.getPhone());
        }
        if(request.getEmail() != null && !request.getEmail().isEmpty()){
            updateUser.setEmail(request.getEmail());
        }
        if(request.getFullName() != null && !request.getFullName().isEmpty()){
            updateUser.setFullName(request.getFullName());
        }

        updateUser.setGender(request.getGender());
        updateUser.setUpdateDate(new Date());

        userRepository.save(updateUser);

        return userMapper.toUserResponse(updateUser);

    }

    // Delete user
    @Override
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        if(user == null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        user.setStatus(StatusEnum.Closed);

        userRepository.save(user);
    }

    @Override
    public void addAddress(Long userId, AddressRequest request){
        AddressEntity newAddress = new AddressEntity();

        newAddress.setAddress(request.getAddress());
        newAddress.setPhone(request.getPhone());

        newAddress.setUser(userRepository.findByIdAndStatus(userId, StatusEnum.Active));

        addressRepository.save(newAddress);
    }

    @Override
    public void deleteAddress(Long addressId){
        addressRepository.deleteById(addressId);
    }

    @Override
    @Transactional
    public String checkout(Long userId, Long addressId){
        OrderEntity newOrder = new OrderEntity();

        UserEntity user = userRepository.findByIdAndStatus(userId, StatusEnum.Active);

        AddressEntity address = addressRepository.findById(addressId).get();
        List<OrderDetailEntity> orderDetails = new ArrayList<>();

        Long totalOrder = 0L;
        for(CartEntity cart : user.getCarts()){
            OrderDetailEntity item = new OrderDetailEntity();

            item.setProduct(cart.getProduct());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getProduct().getPrice());
            item.setDiscount(cart.getProduct().getDiscount());
            item.setTotal(cart.getProduct().getPrice() * (100 - cart.getProduct().getDiscount()) * cart.getQuantity() / 100);
            item.setOrder(newOrder);

            totalOrder += item.getTotal();

            orderDetails.add(item);
            orderDetailRepository.save(item);
            
            ProductEntity product = cart.getProduct();
            product.setQuantity(product.getQuantity() - cart.getQuantity());
            productRepository.save(product);
        }

        newOrder.setUser(user);
        newOrder.setTotal(totalOrder);
        newOrder.setQuantity(Long.valueOf(orderDetails.size()));
        newOrder.setDate(new Date());
        newOrder.setAddress(address.getAddress());
        newOrder.setPhone(address.getPhone());
        newOrder.setStatus(StatusOrderEnum.Dang_Xu_Ly);

        orderRepository.save(newOrder);

        cartRepository.deleteByUserId(userId);

        return "Sản phẩm đang được chuẩn bị để giao, Xem chi tiết tại lịch sử mua hàng";
    }
}
