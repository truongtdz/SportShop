package com.sportshop.sportshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sportshop.sportshop.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserId(Long userId);

    @Query(value = "SELECT DATE(o.date) AS date, SUM(o.total) AS revenue " +
               "FROM orders o WHERE o.date BETWEEN :startDate AND :endDate " +
               "GROUP BY DATE(o.date) " +
               "ORDER BY DATE(o.date) ASC", nativeQuery = true)
    List<Object[]> findRevenueByDay(@Param("startDate") LocalDateTime startDate, 
                                @Param("endDate") LocalDateTime endDate);
}
