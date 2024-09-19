package com.sportshop.sportshop.entity;

import com.sportshop.sportshop.enums.CityEnum;
import com.sportshop.sportshop.enums.DistrictEnum;
import com.sportshop.sportshop.enums.GenderEnum;
import com.sportshop.sportshop.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private CityEnum city;

    @Enumerated(EnumType.STRING)
    @Column(name = "district")
    private DistrictEnum district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "street")
    private String street;

    @Column(name = "email")
    private String email;

    @Column(name = "roles")
    private RoleEnum roles;

    @OneToMany(mappedBy = "user_id", fetch = FetchType.LAZY)
    private List<OrderEntity> orders;

}
