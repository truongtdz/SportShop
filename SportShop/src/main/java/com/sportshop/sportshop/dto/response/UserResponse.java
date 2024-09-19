package com.sportshop.sportshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private long id;
    private String userName;
    private String password;
    private String fullName;
    private String gender;
    private String phone;
    private String email;
    private String address;
}
