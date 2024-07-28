package com.dsac.ecommer_backend.repository;

import com.dsac.ecommer_backend.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {

    @Query(value = "CALL sp_deleteOrderDetailsByOrderId(:idOrder)", nativeQuery = true)
    void deleteAllByOrderId(@Param("idOrder") UUID idOrder);

}
