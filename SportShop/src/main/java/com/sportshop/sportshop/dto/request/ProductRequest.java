package com.sportshop.sportshop.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    Long id;
    String name;
    Long price;
    Long discount;
    String description;
    String color;
    Long quantity;
    Date createDate;
    Date updateDate;
    Long categoryId;
    Long brandId;
}
