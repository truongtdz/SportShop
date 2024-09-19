package com.sportshop.sportshop.entity;

import com.sportshop.sportshop.enums.StatusProductEnum;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "create_day")
    private Date createDay;

    @Column(name = "total")
    private long total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_product")
    private StatusProductEnum statusProduct;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user_id;

    @OneToMany(mappedBy = "order_id",fetch = FetchType.LAZY)
    private List<ItemEntity> items;
}
