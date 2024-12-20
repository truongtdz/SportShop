package com.sportshop.sportshop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.enums.StatusEnum;
import com.sportshop.sportshop.repository.UserRepository;


@Component
public class GetUserAuthentication {
    @Autowired
    private UserRepository userRepository;

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername(); // Username của người dùng
        }
        return null; // Trường hợp không có người dùng nào đăng 
    }
    
    public UserEntity getUser() {
        String username = getUsername();
        if (username != null) {
            return userRepository.findByUsernameAndStatus(username, StatusEnum.Active); // Truy vấn thông tin người dùng
        }
        return null; // Không tìm thấy người dùng
    }
}
