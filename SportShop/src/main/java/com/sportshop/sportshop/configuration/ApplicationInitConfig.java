package com.sportshop.sportshop.configuration;

import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.enums.RoleEnum;
import com.sportshop.sportshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationInitConfig {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                UserEntity user = UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(RoleEnum.ADMIN)
                        .build();
                userRepository.save(user);
            }
        };
    }
}

