package com.sportshop.sportshop.repository;

import com.sportshop.sportshop.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository  extends JpaRepository<ProductDetailEntity, String> {
    List<ProductDetailEntity> findByProduct_Id(Long productId);
}
