package com.sportshop.sportshop.dto.request;

import com.sportshop.sportshop.entity.BrandEntity;
import com.sportshop.sportshop.entity.CategoryEntity;
import com.sportshop.sportshop.entity.ProductDetailEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    Long id;
    String name;
    String color;
    Long price;
    Long discount;
    String description;
    Long quantity;
    Date createDate;
    Date updateDate;
    Long categoryId;
    Long brandId;
    List<ProductDetailEntity> productDetails;
}
