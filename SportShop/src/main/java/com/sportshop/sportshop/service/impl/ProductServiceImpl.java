package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.dto.request.ProductRequest;
import com.sportshop.sportshop.dto.response.ProductResponse;
import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.mapper.ProductMapper;
import com.sportshop.sportshop.repository.ProductRepository;
import com.sportshop.sportshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // Count product
    @Override
    public String countProduct(){
        return String.valueOf(productRepository.count());
    }

    // View all product
    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> products = new ArrayList<>();
        for(ProductEntity item : productRepository.findAll()){
            products.add(productMapper.toProductResponse(item));
        }
        return products;
    }

    // View product by ID
    @Override
    public ProductResponse getProductById(Long productId) {
        return productMapper.toProductResponse(productRepository.findById(productId));
    }

    // Create product
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        ProductEntity newProduct = productMapper.toProductEntity(productRequest);
        return productMapper.toProductResponse(productRepository.save(newProduct));
    }

    // Update Product
    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        ProductEntity updatedProduct = productRepository.findById(String.valueOf(productId)).get();

        productMapper.updateProductEntity(updatedProduct, productRequest);
        productRepository.save(updatedProduct);

        return productMapper.toProductResponse(updatedProduct);
    }

    // Delete Product
    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(String.valueOf(productId));
    }
}
