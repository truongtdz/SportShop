package com.sportshop.sportshop.dto.response;

import com.sportshop.sportshop.entity.OrderDetailEntity;
import com.sportshop.sportshop.entity.UserEntity;
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
    Date orderDate;
    Long total;
    UserEntity user;
    List<OrderDetailEntity> orderDetails;
}
