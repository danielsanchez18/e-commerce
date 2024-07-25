package com.dsac.ecommer_backend.implement;

import com.dsac.ecommer_backend.model.Order;
import com.dsac.ecommer_backend.repository.OrderRepository;
import com.dsac.ecommer_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order not found with id " + orderId)
        );
    }

    @Override
    public List<Order> getOrdersByUser(UUID userId, int page, int size) {
        return orderRepository.getOrdersByUser(userId, page, size);
    }

    @Override
    public Order updateOrder(UUID idOrder, Order savedOrder) {

        Order order = orderRepository.findById(idOrder).orElseThrow(
                () -> new RuntimeException("Order not found with id " + idOrder)
        );

        return null;
    }

    @Override
    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }
}
