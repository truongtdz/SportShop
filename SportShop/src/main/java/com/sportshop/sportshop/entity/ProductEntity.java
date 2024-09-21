package com.sportshop.sportshop.entity;
import com.sportshop.sportshop.enums.BrandEnum;
import com.sportshop.sportshop.enums.CategoryEnum;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.enums.SizeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private CategoryEnum category;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand")
    private BrandEnum brand;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "price")
    private String price;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private SizeEnum size;

    @Column(name = "discount")
    private String discount;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "quantity")
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ItemEntity item_id;
}
