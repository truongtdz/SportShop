package com.sportshop.sportshop.repository;

import com.sportshop.sportshop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findById(Long id);


}
