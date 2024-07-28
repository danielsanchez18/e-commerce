package com.dsac.ecommer_backend.controller;

import com.dsac.ecommer_backend.model.Order;
import com.dsac.ecommer_backend.model.OrderDetail;
import com.dsac.ecommer_backend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop")
@CrossOrigin("*")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<String> addProductToCart(@RequestParam UUID productId, @RequestParam int quantity) {
        try {
            shoppingCartService.addProductToCart(productId, quantity);
            return ResponseEntity.ok("Producto agregado al carrito exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar el producto al carrito.");
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeProductFromCart(@RequestParam UUID productId) {
        try {
            shoppingCartService.removeProductFromCart(productId);
            return ResponseEntity.ok("Producto eliminado del carrito exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar el producto del carrito.");
        }
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearCart() {
        shoppingCartService.clearCart();
        return ResponseEntity.ok("Carrito limpiado exitosamente.");
    }

    @PostMapping("/order")
    public ResponseEntity<Order> generateOrder(@RequestParam String type,
                                               @RequestParam String address,
                                               @RequestParam String phone,
                                               @RequestParam String comment) {
        Order order = shoppingCartService.generateOrder(type, address, phone, comment);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/view-cart")
    public ResponseEntity<List<OrderDetail>> viewCart() {
        List<OrderDetail> cart = shoppingCartService.viewCart();
        if (cart.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(cart);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateProductQuantity(@RequestParam UUID productId, @RequestParam int quantity) {
        shoppingCartService.updateProductQuantity(productId, quantity);
        return ResponseEntity.ok().build();
    }

}