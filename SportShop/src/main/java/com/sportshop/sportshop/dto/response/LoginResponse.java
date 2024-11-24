package com.sportshop.sportshop.dto.response;

import com.sportshop.sportshop.entity.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.String;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
    boolean login;
    UserEntity user;
    String message;
}
