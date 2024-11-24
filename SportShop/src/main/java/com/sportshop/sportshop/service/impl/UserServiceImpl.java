package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.dto.response.UserResponse;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.enums.RoleEnum;
import com.sportshop.sportshop.exception.AppException;
import com.sportshop.sportshop.exception.ErrorCode;
import com.sportshop.sportshop.mapper.UserMapper;
import com.sportshop.sportshop.repository.UserRepository;
import com.sportshop.sportshop.service.CloudinaryService;
import com.sportshop.sportshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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

    // View user quantity
    @Override
    public String countUsers() {
        return String.valueOf(userRepository.count());
    }

    // Find By Username
    @Override
    public UserEntity getUserByUsername(String username) {
        if(!userRepository.existsByUsername(username)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return userRepository.findByUsername(username);
    }

    // View user by Id
    @Override
    public UserResponse getUserById(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        UserResponse userResponse =userMapper.toUserResponse(userRepository.findById(userId).get()) ;
        return userResponse ;
    }

    // View all users
    @Override
    public List<UserResponse> getUsers() {
        List<UserResponse> users = new ArrayList<>();
        for(UserEntity user: userRepository.findAll()){
            users.add(userMapper.toUserResponse(user));
        }
        return users;
    }

    // Create user
    @Override
    public UserEntity createUser(CreateUserRequest request, MultipartFile file) {
        if(userRepository.existsByUsername(request.getUsername())){
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

        return userRepository.save(newUser);
    }

    // Update user
    @Override
    public UserResponse updateUser(UpdateUserRequest request, Long userId) {
        if(!userRepository.existsById(userId)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        UserEntity updateUser = userRepository.findById(userId).get();

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
        if(!userRepository.existsById(userId)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepository.deleteById(userId);
    }

}
