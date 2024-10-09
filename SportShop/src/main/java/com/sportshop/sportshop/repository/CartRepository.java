package com.sportshop.sportshop.repository;

import com.sportshop.sportshop.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {
    // View cart
    @Query(value = "Select * From cart WHere cart.user_id =:userId", nativeQuery = true)
    List<CartEntity> findByUserId(@Param("userId") Long userId);
}
