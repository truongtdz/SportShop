package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.dto.request.ProductRequest;
import com.sportshop.sportshop.dto.request.SearchRequest;
import com.sportshop.sportshop.dto.response.ProductResponse;
import com.sportshop.sportshop.entity.BrandEntity;
import com.sportshop.sportshop.entity.CategoryEntity;
import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.enums.StatusEnum;
import com.sportshop.sportshop.exception.AppException;
import com.sportshop.sportshop.exception.ErrorCode;
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
    public int countProduct(){
        return productRepository.findByStatus(StatusEnum.Active).size();
    }

    // View all product
    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> products = new ArrayList<>();
        for(ProductEntity item : productRepository.findByStatus(StatusEnum.Active)){
            products.add(productMapper.toProductResponse(item));
        }
        return products;
    }

    @Override
    public List<ProductResponse> getProductSale() {
        // Lấy và lọc các sản phẩm có giảm giá (discount > 0) trước khi chuyển đổi
        List<ProductResponse> products = productRepository.findByStatus(StatusEnum.Active).stream()
                .map(productMapper::toProductResponse)   // Chuyển đổi ProductEntity thành ProductResponse
                .sorted(Comparator.comparing(ProductResponse::getDiscount).reversed())  // Sắp xếp theo discount từ cao đến thấp
                .limit(10)  // Lấy 10 sản phẩm giảm giá nhiều nhất
                .collect(Collectors.toList());  // Thu thập kết quả vào danh sách

        return products;  // Trả về danh sách sản phẩm giảm giá
    }


    @Override
    public List<ProductResponse> getProductNewest() {
        List<ProductResponse> products = productRepository.findByStatus(StatusEnum.Active).stream()
                .map(productMapper::toProductResponse)   // Chuyển đổi ProductEntity thành ProductResponse
                .sorted(Comparator.comparing(ProductResponse::getCreateDate).reversed())  // Sắp xếp theo ngày tạo từ mới đến cũ
                .limit(10)  // Lấy 10 sản phẩm mới nhất
                .collect(Collectors.toList());  // Thu thập kết quả vào danh sách

        return products;  // Trả về danh sách sản phẩm mới nhất
    }


    // View product by ID
    @Override
    public ProductResponse getProductById(Long productId) {
        return productMapper.toProductResponse(productRepository.findByIdAndStatus(productId, StatusEnum.Active));
    }

    // Create product
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        if(productRepository.existsByNameAndStatus(productRequest.getName(), StatusEnum.Active)){
            throw new  AppException(ErrorCode.PRODUCT_EXISTED);
        }

        ProductEntity newProduct = productMapper.toProductEntity(productRequest);
        newProduct.setCreateDate(new Date());
        newProduct.setStatus(StatusEnum.Active);

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
    public ProductResponse updateProduct(Long productId, ProductRequest request) {
        if(productRepository.existsByNameAndStatus(request.getName(), StatusEnum.Active)){
            throw new  AppException(ErrorCode.PRODUCT_EXISTED);
        }

        ProductEntity updatedProduct = productRepository.findByIdAndStatus(productId, StatusEnum.Active);

        if(request.getName() != null && !request.getName().isEmpty()){
            updatedProduct.setName(request.getName());
        }
        if(request.getPrice() != null){
            updatedProduct.setPrice(request.getPrice());
        }
        if(request.getDiscount() != null){
            updatedProduct.setDiscount(request.getDiscount());
        }
        if(request.getColor() != null && !request.getColor().isEmpty()){
            updatedProduct.setColor(request.getColor());
        }
        if(request.getQuantity() != null){
            updatedProduct.setQuantity(request.getQuantity());
        }
        if(request.getDescription() != null && !request.getDescription().isEmpty()){
            updatedProduct.setDescription(request.getDescription());
        }

        updatedProduct.setUpdateDate(new Date());

        productRepository.save(updatedProduct);

        return productMapper.toProductResponse(updatedProduct);
    }

    // Delete Product
    @Override
    public void deleteProduct(Long productId) {
        ProductEntity product = productRepository.findByIdAndStatus(productId, StatusEnum.Active);
        
        product.setStatus(StatusEnum.Closed);

        productRepository.save(product);
    }

    @Override
    public List<ProductResponse> searchProductByNameOrDescription(String name) {
        List<ProductResponse> result = new ArrayList<>();
        
        for(ProductEntity item : productRepository.findByNameContainingOrDescriptionContainingAndStatus(name, name, StatusEnum.Active)){
            result.add(productMapper.toProductResponse(item));
        }

        return result;
    }

    @Override
    public List<ProductResponse> searchProduct(SearchRequest request) {
        List<ProductResponse> result = new ArrayList<>();
        
        for(ProductEntity item : productRepository.search(request)){
            result.add(productMapper.toProductResponse(item));
        }

        return result;
    }

}
