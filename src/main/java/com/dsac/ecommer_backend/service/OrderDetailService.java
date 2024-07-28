package com.dsac.ecommer_backend.service;

import com.dsac.ecommer_backend.model.OrderDetail;

import java.util.List;
import java.util.UUID;

public interface OrderDetailService {

    OrderDetail createOrderDetail(OrderDetail orderDetail);

    OrderDetail getOrderDetailById(UUID orderDetailId);

    void deleteOrderDetail(UUID orderDetailId);

    List<OrderDetail> viewOrderDetails (UUID orderId);

}
