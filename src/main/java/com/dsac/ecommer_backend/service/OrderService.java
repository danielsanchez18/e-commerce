package com.dsac.ecommer_backend.service;

import com.dsac.ecommer_backend.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order getOrderById(UUID orderId);

    List<Order> getOrdersByUser(UUID userId, int page, int size);

    Order updateOrder(UUID idOrder, Order savedOrder);

    void deleteOrder(UUID orderId);

}
