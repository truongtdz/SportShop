package com.sportshop.sportshop.entity;

import com.sportshop.sportshop.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "fullname")
    String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    GenderEnum gender;

    @Column(name = "phone")
    String phone;

    @Column(name = "email")
    String email;

    @Column(name = "create_date")
    Date createDate;

    @Column(name = "update_date")
    Date updateDate;

    @Column(name = "roles")
    String roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<OrderEntity> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<CartEntity> carts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<AddressEntity> addresslist;
}
