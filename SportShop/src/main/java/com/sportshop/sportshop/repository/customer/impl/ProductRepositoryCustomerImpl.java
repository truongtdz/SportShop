package com.sportshop.sportshop.repository.customer.impl;

import com.sportshop.sportshop.dto.request.SearchRequest;
import com.sportshop.sportshop.entity.ProductEntity;
import com.sportshop.sportshop.enums.SortEnum;
import com.sportshop.sportshop.repository.customer.ProductRepositoryCustomer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryCustomerImpl implements ProductRepositoryCustomer {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductEntity> search(SearchRequest newSearch) {
        StringBuilder sql = new StringBuilder("SELECT * FROM product WHERE status_product = 'Active' ");

        if(newSearch.getMinPrice() != null){
            sql.append(" AND price >= " + newSearch.getMinPrice());
        }

        if(newSearch.getMaxPrice() != null){
            sql.append(" AND price <= " + newSearch.getMaxPrice());
        }

        if(newSearch.getCategories() != null && newSearch.getCategories().size() > 0){
            List<String> categories = newSearch.getCategories().stream()
                    .map(category -> "'" + category + "'") // thêm dấu nháy đơn nếu là chuỗi
                    .collect(Collectors.toList());

            String categoriesSql = String.join(", ", categories); // tạo chuỗi danh sách phân cách bằng dấu phẩy

            sql.append(" AND category_id IN (" + categoriesSql + ")");
        }

        if(newSearch.getBrands() != null && newSearch.getBrands().size() > 0){
            List<String> brands = newSearch.getBrands().stream()
                    .map(brand -> "'" + brand + "'") // thêm dấu nháy đơn nếu là chuỗi
                    .collect(Collectors.toList());

            String brandsSql = String.join(", ", brands); // tạo chuỗi danh sách phân cách bằng dấu phẩy

            sql.append(" AND brand_id IN (" + brandsSql + ")");
        }

        if(newSearch.getTypeSort().equals(SortEnum.DECS_PRICE)){
            sql.append(" order by price desc ");
        }

        if(newSearch.getTypeSort().equals(SortEnum.ORDER_PRICE)){
            sql.append(" order by price ");
        }

        if(newSearch.getTypeSort().equals(SortEnum.NEW)){
            sql.append(" order by create_date ");
        }

        if(newSearch.getTypeSort().equals(SortEnum.SALE)){
            sql.append(" order by discount ");
        }

        Query query = entityManager.createNativeQuery(sql.toString(), ProductEntity.class);
        return query.getResultList();
    }
}
