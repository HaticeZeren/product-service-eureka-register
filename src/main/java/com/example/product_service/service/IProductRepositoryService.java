package com.example.product_service.service;
import com.example.product_service.repository.entity.Product;
import com.example.product_service.request.ProductCreateRequest;

import java.util.List;

public interface IProductRepositoryService {

    Product createProduct(ProductCreateRequest productCreateRequest);

    List<Product> findAll();
}
