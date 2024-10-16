package com.sportshop.sportshop.entity;

import com.sportshop.sportshop.enums.BrandEnum;
import com.sportshop.sportshop.enums.CategoryEnum;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.enums.SizeEnum;
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
    Long productId;

    @Column(name = "name")
    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    GenderEnum gender;

    @Column(name = "color")
    String color;

    @Column(name = "price")
    Long price;

    @Column(name = "discount")
    Long discount;

    @Column(name = "description")
    String description;

    @Column(name = "image")
    String image;

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "date")
    Date date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    BrandEntity brand;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<ProductDetailEntity> productDetails;
}
