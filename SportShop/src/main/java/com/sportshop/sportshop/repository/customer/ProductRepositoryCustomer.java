package com.sportshop.sportshop.repository.customer; 

import org.springframework.stereotype.Repository;

import com.sportshop.sportshop.dto.request.SearchRequest;
import com.sportshop.sportshop.entity.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepositoryCustomer {
    List<ProductEntity> search(SearchRequest request);
}
