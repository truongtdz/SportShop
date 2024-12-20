package com.sportshop.sportshop.repository;

import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.enums.StatusEnum;
import com.sportshop.sportshop.repository.customer.ProductRepositoryCustomer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryCustomer {
    boolean existsByNameAndStatus(String name, StatusEnum status);
    List<ProductEntity> findByStatus(StatusEnum status);
    boolean existsByIdAndStatus(Long id, StatusEnum status);
    ProductEntity findByNameAndStatus(String name, StatusEnum status);
    ProductEntity findByIdAndStatus(Long productId, StatusEnum status);
    List<ProductEntity> findByNameContainingOrDescriptionContainingAndStatus(String name, String description, StatusEnum status);
}
