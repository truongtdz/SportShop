package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.dto.response.UserResponse;
import com.sportshop.sportshop.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public UserEntity createUser(CreateUserRequest request, MultipartFile file);

    // Update user
    public UserResponse updateUser(UpdateUserRequest request, Long userId);

    // Delete user
    public void deleteUser(Long userId);

    // Find By Username
    public UserEntity getUserByUsername(String username);

}
