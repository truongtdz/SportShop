package com.sportshop.sportshop.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
    private Long id;
    private Long quantity;
    private Long orderId;
    private Long productId;
}
