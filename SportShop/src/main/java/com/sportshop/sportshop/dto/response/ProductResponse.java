package com.sportshop.sportshop.dto.response;

import com.sportshop.sportshop.entity.BrandEntity;
import com.sportshop.sportshop.entity.CategoryEntity;
import com.sportshop.sportshop.entity.ImageEntity;
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
public class ProductResponse {
    Long id;
    String name;
    String color;
    Long price;
    Long discount;
    String description;
    Long quantity;
    Date createDate;
    Date updateDate;
    CategoryEntity category;
    BrandEntity brand;
    List<ImageEntity> images;
    List<ProductDetailEntity> productDetails;
}
