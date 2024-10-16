package com.sportshop.sportshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private Set<String> roles;
}
