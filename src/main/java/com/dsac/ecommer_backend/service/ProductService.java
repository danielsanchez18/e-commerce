package com.dsac.ecommer_backend.service;

import com.dsac.ecommer_backend.exception.ResourceFoundException;
import com.dsac.ecommer_backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product addProduct (Product product) throws ResourceFoundException;

    Page<Product> getAllProducts (Pageable pageable);

    Product getProductByName (String name);

    List<Product> searchProductsByName(String name, int page, int size);

    List<Product> getEnabledProducts (int page, int size);

    List<Product> getDisabledProducts (int page, int size);

    List<Product> getProductsByCategory (String category, int page, int size);

    List<Product> getEnabledProductsByCategory (String category, int page, int size);

    Product getProductById (UUID id) throws ResourceFoundException;

    List<Product> getTopSellingProducts (int page, int size);

    List<Product> getTopBuyingProducts (int page, int size);

    Product getTopBuyerProduct(UUID idUser);

    Product updateProduct (UUID id, Product product) throws ResourceFoundException;

    void deleteProduct (UUID id);
}
