package com.sportshop.sportshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillResponse {
    private Long orderId;
    private String nameProduct ;
    private Long price;
    private Long quantity;
    private Long total;

    public BillResponse(Long orderId, String nameProduct, Long price, Long quantity) {
        this.orderId = orderId;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
    }
}
