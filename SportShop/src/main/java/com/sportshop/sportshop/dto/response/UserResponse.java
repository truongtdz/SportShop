package com.sportshop.sportshop.dto.response;

import com.sportshop.sportshop.entity.AddressEntity;
import com.sportshop.sportshop.entity.CartEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String username;
    String password;
    String fullName;
    String gender;
    String phone;
    String email;
    String avatar;
    Date createDate;
    Date updateDate;
    String roles;
    List<CartEntity> carts;
    List<AddressEntity> addressList;
}
