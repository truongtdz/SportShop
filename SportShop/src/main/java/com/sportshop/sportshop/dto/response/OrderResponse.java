package com.sportshop.sportshop.dto.response;

import com.sportshop.sportshop.entity.OrderDetailEntity;
import com.sportshop.sportshop.entity.UserEntity;
import com.sportshop.sportshop.enums.StatusOrderEnum;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;
    Date date;
    Long total;
    Long quantity;
    String phone;
    String address;
    StatusOrderEnum status;
    UserEntity user;
    List<OrderDetailEntity> items;
}
