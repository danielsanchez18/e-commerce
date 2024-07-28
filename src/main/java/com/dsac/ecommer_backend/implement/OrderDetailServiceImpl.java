package com.dsac.ecommer_backend.implement;

import com.dsac.ecommer_backend.model.Order;
import com.dsac.ecommer_backend.model.OrderDetail;
import com.dsac.ecommer_backend.repository.OrderDetailRepository;
import com.dsac.ecommer_backend.service.OrderDetailService;
import com.dsac.ecommer_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailById(UUID orderDetailId) {
        return orderDetailRepository.findById(orderDetailId).
                orElseThrow(() -> new RuntimeException("OrderDetail not found"));
    }

    @Override
    public void deleteOrderDetail(UUID orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }

    @Override
    public List<OrderDetail> viewOrderDetails(UUID orderId) {
        Order order= orderService.getOrderById(orderId);

        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        return new ArrayList<>(order.getOrderDetails());
    }
}
