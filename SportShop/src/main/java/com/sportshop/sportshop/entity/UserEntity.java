package com.sportshop.sportshop.entity;

import com.sportshop.sportshop.enums.RoleEnum;
import com.sportshop.sportshop.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
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

    @Column(name = "full_name")
    String fullName;

    @Column(name = "gender")
    String gender;

    @Column(name = "phone")
    String phone;

    @Column(name = "email")
    String email;

    @Column(name = "create_date")
    Date createDate;

    @Column(name = "update_date")
    Date updateDate;

    @Column(name = "avatar")
    String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    RoleEnum roles;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_user")
    StatusEnum status;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<OrderEntity> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<CartEntity> carts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<AddressEntity> addresses;
}
