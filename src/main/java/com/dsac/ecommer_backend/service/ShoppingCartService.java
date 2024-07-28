package com.dsac.ecommer_backend.service;

import com.dsac.ecommer_backend.model.Order;
import com.dsac.ecommer_backend.model.OrderDetail;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {

    Order generateOrder (String type, String address, String phone, String comment);

    void addProductToCart (UUID productId, int quantity);

    void removeProductFromCart (UUID productId);

    void clearCart();

    List<OrderDetail> viewCart();

    void updateProductQuantity (UUID productId, int quantity);

}