package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.dto.response.UserResponse;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.enums.RoleEnum;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    // View user quantity
    public String countUsers() {
        return String.valueOf(userRepository.count());
    }

    // View all users
    public List<UserResponse> getUsers() {
        List<UserResponse> users = new ArrayList<>();
        for(UserEntity user: userRepository.findAll()){
            users.add(userMapper.toUserResponse(user));
        }
        return users;
    }

    // View user by Id
    public UserResponse getUserById(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        UserResponse userResponse =userMapper.toUserResponse(userRepository.findById(userId)) ;
        return userResponse ;
    }

    // Create user
    public UserEntity createUser(CreateUserRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if(request.getRoles() == null){
            request.setRoles(RoleEnum.USER);
        }
        UserEntity newUser = userMapper.toUserEntity(request);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }

    // Update user
    public UserResponse updateUser(UpdateUserRequest request, Long userId) {
        if(!userRepository.existsById(userId)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        UserEntity updateUser = userRepository.findById(userId);

        updateUser.setPassword(passwordEncoder.encode(request.getPassword()));

        userMapper.updateUserEntity(updateUser, request);
        userRepository.save(updateUser);

        return userMapper.toUserResponse(updateUser);
    }

    // Delete user
    public void deleteUser(String userId) {
        if(!userRepository.existsById(userId)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepository.deleteById(userId);
    }

    // Find By Username
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
