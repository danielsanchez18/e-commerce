package com.dsac.ecommer_backend.service;

import com.dsac.ecommer_backend.model.Order;

import java.util.UUID;

public interface ShoppingCartService {

    Order generateOrder (String type, String address, String phone, String comment);

    void addProductToCart (UUID productId, int quantity);

    void removeProductFromCart (UUID productId);

    void clearCart();

    void viewCart();

    void updateProductQuantity (UUID productId, int quantity);

}