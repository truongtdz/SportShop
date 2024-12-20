package com.sportshop.sportshop.dto.request;

import java.util.List;

import com.sportshop.sportshop.enums.SortEnum;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchRequest {
    Long minPrice;
    Long maxPrice;
    List<String> brands;
    List<String> categories;
    SortEnum typeSort;
}
