package com.sportshop.sportshop.dto.response;

import com.sportshop.sportshop.entity.ProductEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandResponse {
    Long id;
    String name; 
    String image;
    List<ProductEntity> products;
}
