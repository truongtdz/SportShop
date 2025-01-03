package com.sportshop.sportshop.repository;


import com.sportshop.sportshop.entity.CartEntity;
import com.sportshop.sportshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findById(Long id);
    boolean existsById(Long id);
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);

    // View cart
    @Query(value = "Select * From cart WHere cart.user_id =:userId", nativeQuery = true)
    CartEntity findByUserId(@Param("userId") Long userId);
}
