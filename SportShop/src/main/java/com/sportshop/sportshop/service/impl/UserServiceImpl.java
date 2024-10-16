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
import com.sportshop.sportshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    // View user quantity
    @Override
    public String countUsers() {
        return String.valueOf(userRepository.count());
    }

    // Find By Username
    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // View all users
    @Override
    public List<UserResponse> getUsers() {
        List<UserResponse> users = new ArrayList<>();
//        for(UserEntity user: userRepository.findAll()){
//            users.add(userMapper.toUserResponse(user));
//        }
        return users;
    }

    // View user by Id
    @Override
    public UserResponse getUserById(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

//        UserResponse userResponse =userMapper.toUserResponse(userRepository.findById(userId)) ;
        return null ;
    }

    // Create user
    @Override
    public UserEntity createUser(CreateUserRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if(request.getRoles() == null){
            request.setRoles(RoleEnum.USER);
        }
//        UserEntity newUser = userMapper.toUserEntity(request);
//
//        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
//
//        return userRepository.save(newUser);

        return null;
    }

    // Update user
    @Override
    public UserResponse updateUser(UpdateUserRequest request, Long userId) {
        if(!userRepository.existsById(userId)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        UserEntity updateUser = userRepository.findById(userId);

        request.setPassword(passwordEncoder.encode(request.getPassword()));

//        userMapper.updateUserEntity(updateUser, request);
//        userRepository.save(updateUser);
//
//        return userMapper.toUserResponse(updateUser);

        return null;
    }

    // Delete user
    @Override
    public void deleteUser(String userId) {
        if(!userRepository.existsById(userId)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepository.deleteById(userId);
    }

}
