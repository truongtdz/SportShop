package com.sportshop.sportshop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    Long price;

    @Column(name = "discount")
    Long discount;

    @Column(name = "description")
    String description;

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "create_date")
    Date createDate;

    @Column(name = "update_date")
    Date updateDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    BrandEntity brand;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<ImageEntity> images;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<ProductDetailEntity> productDetails;
}
