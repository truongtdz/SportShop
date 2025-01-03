package com.sportshop.sportshop.dto.request;

import com.sportshop.sportshop.enums.BrandEnum;
import com.sportshop.sportshop.enums.CategoryEnum;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.enums.SizeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    Long id;
    CategoryEnum category;
    BrandEnum brand;
    String name;
    GenderEnum gender;
    Long price;
    String color;
    SizeEnum size;
    Long discount;
    String description;
    String image;
    Long quantity;
}
