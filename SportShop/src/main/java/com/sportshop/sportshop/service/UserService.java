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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface UserService {

    // View user quantity
    public String countUsers();

    // View all users
    public List<UserResponse> getUsers();

    // View user by Id
    public UserResponse getUserById(Long userId);

    // Create user
    public UserEntity createUser(CreateUserRequest request);

    // Update user
    public UserResponse updateUser(UpdateUserRequest request, Long userId);

    // Delete user
    public void deleteUser(String userId);

    // Find By Username
    public UserEntity getUserByUsername(String username);

}
