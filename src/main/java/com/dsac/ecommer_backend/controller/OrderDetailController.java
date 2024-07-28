package com.dsac.ecommer_backend.controller;

import com.dsac.ecommer_backend.model.OrderDetail;
import com.dsac.ecommer_backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order-detail")
@CrossOrigin("*")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/save")
    public void saveOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailService.createOrderDetail(orderDetail);
    }

    @GetMapping("/id/{id}")
    public OrderDetail getOrderDetailById(@PathVariable UUID id) {
        return orderDetailService.getOrderDetailById(id);
    }

    @GetMapping("/order/{orderId}")
    public void viewOrderDetails(@PathVariable UUID orderId) {
        orderDetailService.viewOrderDetails(orderId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrderDetail(@PathVariable UUID id) {
        orderDetailService.deleteOrderDetail(id);
    }
}
