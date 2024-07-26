package com.dsac.ecommer_backend.controller;

import com.dsac.ecommer_backend.exception.ResourceFoundException;
import com.dsac.ecommer_backend.model.Product;
import com.dsac.ecommer_backend.repository.ProductRepository;
import com.dsac.ecommer_backend.service.ProductService;
import com.dsac.ecommer_backend.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private UploadFileService uploadFileService;


//    @PostMapping("/save")
//    public Product addProduct(@RequestBody Product product) throws ResourceFoundException {
//        return productService.addProduct(product);
//    }

    @PostMapping("/save")
    public Product addProduct(@RequestPart("product") Product product,
                              @RequestPart("image") MultipartFile image) throws ResourceFoundException, IOException {
        return productService.addProduct(product, image);
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

//    @PutMapping("/update/{id}")
//    public Product updateProduct(@PathVariable UUID id, @RequestBody Product product) throws ResourceFoundException {
//        return productService.updateProduct(id, product);
//    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable UUID id,
                                 @RequestPart ("product") Product product,
                                 @RequestPart ("file") MultipartFile image) throws ResourceFoundException, IOException {
        return productService.updateProduct(id, product, image);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }
}