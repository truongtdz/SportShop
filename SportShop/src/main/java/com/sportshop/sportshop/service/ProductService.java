package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.request.ProductRequest;
import com.sportshop.sportshop.dto.response.ProductResponse;
import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.mapper.ProductMapper;
import com.sportshop.sportshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ProductService {

    // Count product
    public String countProduct();

    // View all product
    public List<ProductResponse> getAllProducts();

    // View list product sale
    public List<ProductResponse> getProductSale();

    // View list product the newest
    public List<ProductResponse> getProductNewest();

    // View product by ID
    public ProductResponse getProductById(Long productId);

    // Create product
    public ProductResponse createProduct(ProductRequest productRequest);

    // Update Product
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest);

    // Delete Product
    public void deleteProduct(Long productId);

}
