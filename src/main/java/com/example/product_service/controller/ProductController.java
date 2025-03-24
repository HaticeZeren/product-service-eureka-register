package com.example.product_service.controller;
import com.example.product_service.repository.entity.Product;
import com.example.product_service.request.ProductCreateRequest;
import com.example.product_service.service.IProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
class ProductController {
    private final IProductRepositoryService productRepositoryService;

    @Autowired
    ProductController(IProductRepositoryService productRepositoryService) {
        this.productRepositoryService = productRepositoryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create")
    public Product createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        return productRepositoryService.createProduct(productCreateRequest);
    }

    @GetMapping(value = "/findAll")
    public List<Product> findAll() {
        return productRepositoryService.findAll();
    }
}
