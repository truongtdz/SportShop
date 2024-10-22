package com.sportshop.sportshop.dto.request;

import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.enums.RoleEnum;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    String fullName;
    GenderEnum gender;
    String phone;
    String email;
}
