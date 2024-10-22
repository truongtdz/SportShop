package com.sportshop.sportshop.entity;

import com.sportshop.sportshop.enums.GenderEnum;
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
    Long id;

    @Column(name = "color")
    String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    SizeEnum size;

    @Column(name = "quantity")
    Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    GenderEnum gender;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

}
