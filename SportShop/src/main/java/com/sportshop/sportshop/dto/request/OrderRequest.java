package com.sportshop.sportshop.dto.request;

import com.sportshop.sportshop.entity.ItemEntity;
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
public class OrderRequest {
    Long id;
    Date orderDate;
    Long total;
    Long userId;
    List<ItemEntity> items;
}