package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.dto.request.ProductRequest;
import com.sportshop.sportshop.dto.response.ProductResponse;
import com.sportshop.sportshop.entity.BrandEntity;
import com.sportshop.sportshop.entity.CategoryEntity;
import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.mapper.ProductMapper;
import com.sportshop.sportshop.repository.BrandRepository;
import com.sportshop.sportshop.repository.CategoryRepository;
import com.sportshop.sportshop.repository.ProductRepository;
import com.sportshop.sportshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @Override
    public List<ProductResponse> getProductSale() {
        // Lấy và lọc các sản phẩm có giảm giá (discount > 0) trước khi chuyển đổi
        List<ProductResponse> products = productRepository.findAll().stream()
                .filter(item -> item.getDiscount() > 0)  // Lọc các sản phẩm có discount > 0
                .map(productMapper::toProductResponse)   // Chuyển đổi ProductEntity thành ProductResponse
                .sorted(Comparator.comparing(ProductResponse::getDiscount).reversed())  // Sắp xếp theo discount từ cao đến thấp
                .limit(10)  // Lấy 10 sản phẩm giảm giá nhiều nhất
                .collect(Collectors.toList());  // Thu thập kết quả vào danh sách

        return products;  // Trả về danh sách sản phẩm giảm giá
    }


    @Override
    public List<ProductResponse> getProductNewest() {
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
        newProduct.setCreateDate(new Date());

        // Tìm BrandEntity từ brandId
        BrandEntity brand = brandRepository.getBrandById(productRequest.getBrandId());
        newProduct.setBrand(brand);

        // Tìm CategoryEntity từ categoryId
        CategoryEntity category = categoryRepository.getCategoryById(productRequest.getCategoryId());
        newProduct.setCategory(category);

        return productMapper.toProductResponse(productRepository.save(newProduct));
    }

    // Update Product
    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        ProductEntity updatedProduct = productRepository.findById(String.valueOf(productId)).get();

        productMapper.updateProductEntity(updatedProduct, productRequest);

        updatedProduct.setUpdateDate(new Date());
        productRepository.save(updatedProduct);

        return productMapper.toProductResponse(updatedProduct);
    }

    // Delete Product
    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(String.valueOf(productId));
    }
}
