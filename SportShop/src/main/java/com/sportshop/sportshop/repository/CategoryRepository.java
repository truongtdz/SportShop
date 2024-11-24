package com.sportshop.sportshop.repository;

import com.sportshop.sportshop.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity getCategoryById(Long categoryId);
}
