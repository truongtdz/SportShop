package com.sportshop.sportshop.repository;

import com.sportshop.sportshop.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {
    List<ImageEntity> findByProduct_Id(Long productId);
}
