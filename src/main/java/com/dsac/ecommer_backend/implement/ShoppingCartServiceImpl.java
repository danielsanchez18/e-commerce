package com.dsac.ecommer_backend.implement;

import com.dsac.ecommer_backend.model.Order;
import com.dsac.ecommer_backend.model.OrderDetail;
import com.dsac.ecommer_backend.model.Product;
import com.dsac.ecommer_backend.repository.OrderDetailRepository;
import com.dsac.ecommer_backend.repository.OrderRepository;
import com.dsac.ecommer_backend.repository.ProductRepository;
import com.dsac.ecommer_backend.service.ShoppingCartService;
import com.dsac.ecommer_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private List<OrderDetail> cart = new ArrayList<>();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Override
    public Order generateOrder(String type, String address, String phone, String comment) {
        Order order = new Order();
        order.setDate(LocalDateTime.now());
        order.setType(type);
        order.setAddress(address);
        order.setPhone(phone);
        order.setComment(comment);
        order.setStatus("PENDIENTE");
        order.setWaitingTime(20);
        order.setUser(userService.getCurrentUser());

        if ("RECOJO EN TIENDA".equalsIgnoreCase(type)) {
            order.setDeliveryPrice(0);
        }
        else if ("ENTREGA A DOMICILIO".equalsIgnoreCase(type)) {
            order.setDeliveryPrice(5);
        }
        else {
            throw new RuntimeException("Invalid order type");
        }

        order.setTotalPrice(cart.stream().mapToDouble(OrderDetail::getTotalPrice).sum() + order.getDeliveryPrice());

        Order savedOrder = orderRepository.save(order);

        for (OrderDetail orderDetail : cart) {
            orderDetail.setOrder(savedOrder);
            orderDetailRepository.save(orderDetail);
        }

        clearCart();
        return savedOrder;
    }

    @Override
    public void addProductToCart(UUID productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        OrderDetail existingOrderDetail = cart.stream()
                .filter(orderDetail -> orderDetail.getProduct().getIdProduct().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingOrderDetail != null) {
            existingOrderDetail.setQuantity(existingOrderDetail.getQuantity() + quantity);
            existingOrderDetail.setTotalPrice(existingOrderDetail.getQuantity() * existingOrderDetail.getUnitPrice());
        } else {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(quantity);
            orderDetail.setUnitPrice(product.getPrice());
            orderDetail.setTotalPrice(product.getPrice() * quantity);
            cart.add(orderDetail);
        }
    }

    @Override
    public void removeProductFromCart(UUID productId) {
        cart.removeIf(orderDetail -> orderDetail.getProduct().getIdProduct().equals(productId));
    }

    @Override
    public void clearCart() {
        cart.clear();
    }

    @Override
    public List<OrderDetail> viewCart() {
        return new ArrayList<>(cart);
    }

    @Override
    public void updateProductQuantity(UUID productId, int quantity) {
        OrderDetail orderDetail = cart.stream()
                .filter(od -> od.getProduct().getIdProduct().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        orderDetail.setQuantity(quantity);
        orderDetail.setTotalPrice(orderDetail.getUnitPrice() * quantity);
    }
}
