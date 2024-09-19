package com.sportshop.sportshop.api.admin;

import com.sportshop.sportshop.dto.request.CreateUserRequest;
import com.sportshop.sportshop.dto.request.UpdateUserRequest;
import com.sportshop.sportshop.dto.response.ApiResponse;
import com.sportshop.sportshop.dto.response.UserResponse;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAPI {
    @Autowired
    private UserService userService;

    // View all users 
    @GetMapping
    private List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    // View user by id
    @GetMapping("/{user_id}")
    private ApiResponse<UserResponse> getUser(@PathVariable Long user_id) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse();

        apiResponse.setResult(userService.getUserById(user_id));

        return apiResponse;
    }

    // Create user
    @PostMapping
    private ApiResponse<UserEntity> createUser(@RequestBody @Valid CreateUserRequest request) {
        ApiResponse<UserEntity> apiResponse = new ApiResponse();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    // Update user
    @PostMapping("/{user_id}")
    private ApiResponse<UserResponse> updateUser(@RequestBody @Valid UpdateUserRequest request,
                                                 @PathVariable Long user_id){
        ApiResponse<UserResponse> apiResponse = new ApiResponse();

        apiResponse.setResult(userService.updateUser(request, user_id));

        return apiResponse;
    }

    @DeleteMapping("/{user_id}")
    private ApiResponse<UserResponse> deleteUser(@PathVariable String user_id){
        userService.deleteUser(user_id);
        return new ApiResponse();
    }

}
