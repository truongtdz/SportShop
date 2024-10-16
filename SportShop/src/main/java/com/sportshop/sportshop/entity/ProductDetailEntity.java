package com.sportshop.sportshop.entity;

import com.sportshop.sportshop.enums.SizeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_detail")
public class ProductDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productDetailId;

    @Column(name = "size")
    SizeEnum sizeProduct;

    @Column(name = "quantity")
    Long quantityProductDetail;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

}
