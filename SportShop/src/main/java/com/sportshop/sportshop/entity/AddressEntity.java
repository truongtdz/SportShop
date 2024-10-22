package com.sportshop.sportshop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table (name = "address_user")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "city")
    String city;

    @Column(name = "district")
    String district;

    @Column(name = "ward")
    String ward;

    @Column(name = "street")
    String street;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
