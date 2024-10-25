package com.sportshop.sportshop.dto.response;

import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.enums.SizeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
    Long id;
    String color;
    SizeEnum size;
    Long quantity;
    GenderEnum gender;
    ProductEntity product;
}
