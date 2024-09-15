package com.sportshop.sportshop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity_item")
    private long quantity;

    @OneToMany(mappedBy = "item_id", fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order_id;
}
