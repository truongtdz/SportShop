package com.sportshop.sportshop.dto.response;

import com.sportshop.sportshop.enums.BrandEnum;
import com.sportshop.sportshop.enums.CategoryEnum;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.enums.SizeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
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
    Date date;
}
