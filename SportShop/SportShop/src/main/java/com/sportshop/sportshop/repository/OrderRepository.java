package com.sportshop.sportshop.repository;

import com.sportshop.sportshop.dto.response.BillResponse;
import com.sportshop.sportshop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    @Query("Select new com.sportshop.sportshop.dto.response.BillResponse(i.order.id, p.name, p.price, i.quantity) " +
            "From ItemEntity i Join i.product p " +
            "Where i.order.id = :orderId")
    List<BillResponse> getBillByOrderId(@Param("orderId") String orderId);
}
