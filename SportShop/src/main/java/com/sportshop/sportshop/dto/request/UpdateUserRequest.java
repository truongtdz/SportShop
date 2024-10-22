package com.sportshop.sportshop.dto.request;

import com.sportshop.sportshop.enums.GenderEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {
    String password;
    String fullName;
    GenderEnum gender;
    String phone;
    String email;
}
