package com.sportshop.sportshop.service;


import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.dto.response.UserResponse;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.exception.AppException;
import com.sportshop.sportshop.exception.ErrorCode;
import com.sportshop.sportshop.mapper.UserMapper;
import com.sportshop.sportshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    // View all users
    public List<UserResponse> getUsers() {
        List<UserResponse> users = new ArrayList<>();
        for(UserEntity user: userRepository.findAll()){
            users.add(userMapper.toUserResponse(user));
        }
        return users;
    }

    // View user by Id
    public UserResponse getUserById(Long id) {
        if(!userRepository.existsById(id)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return userMapper.toUserResponse(userRepository.findById(id)) ;
    }

    // Create user
    public UserEntity createUser(CreateUserRequest request) {
        if(userRepository.existsByUserName(request.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        UserEntity newUser = userMapper.toUserEntity(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }

    // Update user
    public UserResponse updateUser(UpdateUserRequest request, Long id) {
        if(!userRepository.existsById(id)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        UserEntity updateUser = userRepository.findById(id);

        userMapper.updateUserEntity(updateUser, request);
        userRepository.save(updateUser);

        return userMapper.toUserResponse(updateUser);
    }

    // Delete user
    public void deleteUser(String id) {
        if(!userRepository.existsById(id)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepository.deleteById(id);
    }

}
