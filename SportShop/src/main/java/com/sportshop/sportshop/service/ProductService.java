package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.request.ProductRequest;
import com.sportshop.sportshop.dto.request.ProductResponse;
import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.mapper.ProductMapper;
import com.sportshop.sportshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // Count product
    public String countProduct(){
        return String.valueOf(productRepository.count());
    }

    // View all product
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> products = new ArrayList<>();
        for(ProductEntity item : productRepository.findAll()){
            products.add(productMapper.toProductResponse(item));
        }
        return products;
    }

    // View product by ID
    public ProductResponse getProductById(Long productId) {
        return productMapper.toProductResponse(productRepository.findById(String.valueOf(productId)).get());
    }

    // Create product
    public ProductEntity createProduct(ProductRequest productRequest) {
        ProductEntity newProduct = productMapper.toProductEntity(productRequest);
        return productRepository.save(newProduct);
    }
}
