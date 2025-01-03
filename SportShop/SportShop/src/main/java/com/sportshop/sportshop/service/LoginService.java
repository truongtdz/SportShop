package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.request.LoginRequest;
import com.sportshop.sportshop.dto.response.LoginResponse;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.exception.AppException;
import com.sportshop.sportshop.exception.ErrorCode;
import com.sportshop.sportshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse checkLogin(LoginRequest request){
        if(!userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        LoginResponse response = new LoginResponse();

        UserEntity user = userRepository.findByUsername(request.getUsername());

        if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
            response.setLogin(true);
            response.setUser(user);
            return response;
        } else {
            response.setLogin(false);
            return response;
        }
    }
}
