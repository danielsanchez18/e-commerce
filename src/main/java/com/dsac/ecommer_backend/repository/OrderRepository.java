package com.dsac.ecommer_backend.repository;

import com.dsac.ecommer_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query(value = "CALL sp_get_orders_by_user(:userId, :page, :size)", nativeQuery = true)
    List<Order> getOrdersByUser(@Param("userId") UUID userId, @Param("page") int page, @Param("size") int size);

}