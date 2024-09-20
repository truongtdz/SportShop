package com.sportshop.sportshop.dto.request;

import com.sportshop.sportshop.enums.CityEnum;
import com.sportshop.sportshop.enums.DistrictEnum;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.enums.RoleEnum;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private String phone;
    private CityEnum city;
    private DistrictEnum district;
    private String ward;
    private String street;
    private String email;
    private RoleEnum roles;
}
