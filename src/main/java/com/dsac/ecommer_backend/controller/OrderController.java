package com.dsac.ecommer_backend.controller;

import com.dsac.ecommer_backend.model.Order;
import com.dsac.ecommer_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable UUID id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID cannot be null");
        }
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable UUID userId, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return orderService.getOrdersByUser(userId, pageable.getPageNumber(), pageable.getPageSize());
    }

    @PutMapping("/update/{id}")
    public Order updateOrder(@PathVariable UUID id , @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
    }
}
