package com.dsac.ecommer_backend.controller;

import com.dsac.ecommer_backend.exception.ResourceFoundException;
import com.dsac.ecommer_backend.model.Product;
import com.dsac.ecommer_backend.repository.ProductRepository;
import com.dsac.ecommer_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/save")
    public Product addProduct(@RequestBody Product product) throws ResourceFoundException {
        return productService.addProduct(product);
    }

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @GetMapping("/search/{name}")
    public List<Product> searchProductByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return productService.searchProductsByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/id/{id}")
    public Product getProductById(@PathVariable UUID id) throws ResourceFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/total-products")
    public Long getTotalProducts() {
        return productRepository.count();
    }

    @GetMapping("/all")
    public Page<Product> getAllProducts(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/enabled")
    public List<Product> getEnabledProducts(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return productService.getEnabledProducts(pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/disabled")
    public List<Product> getDisabledProducts(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return productService.getDisabledProducts(pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return productService.getProductsByCategory(category, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/category-enabled/{category}")
    public List<Product> getEnabledProductsByCategory(@PathVariable String category, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return productService.getEnabledProductsByCategory(category, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/top-selling")
    public List<Product> getTopSellingProducts(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return productService.getTopSellingProducts(pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/top-buying")
    public List<Product> getTopBuyingProducts(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return productService.getTopBuyingProducts(pageable.getPageNumber(), pageable.getPageSize());
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable UUID id, @RequestBody Product product) throws ResourceFoundException {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }
}