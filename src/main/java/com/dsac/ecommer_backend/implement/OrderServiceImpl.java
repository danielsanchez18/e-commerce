package com.dsac.ecommer_backend.implement;

import com.dsac.ecommer_backend.model.Order;
import com.dsac.ecommer_backend.model.OrderDetail;
import com.dsac.ecommer_backend.repository.OrderDetailRepository;
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
    @Autowired
    private OrderDetailRepository orderDetailRepository;

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

        order.setType(savedOrder.getType());
        order.setAddress(savedOrder.getAddress());
        order.setPhone(savedOrder.getPhone());
        order.setComment(savedOrder.getComment());
        order.setStatus(savedOrder.getStatus());
        order.setWaitingTime(savedOrder.getWaitingTime());
        order.setDeliveryPrice(savedOrder.getDeliveryPrice());
        order.setTotalPrice(savedOrder.getTotalPrice());

        if (savedOrder.getOrderDetails() != null) {
            orderDetailRepository.deleteAllByOrderId(idOrder);

            for (OrderDetail detail : savedOrder.getOrderDetails()) {
                detail.setOrder(order);
                orderDetailRepository.save(detail);
            }
        }
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }
}
