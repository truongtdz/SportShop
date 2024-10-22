package com.sportshop.sportshop.dto.request;

import com.sportshop.sportshop.entity.OrderEntity;
import com.sportshop.sportshop.entity.ProductEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailRequest {
    Long id;
    Long quantity;
    OrderEntity order;
    ProductEntity product;
}
