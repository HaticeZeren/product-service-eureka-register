package com.example.product_service.service.impl;

import com.example.product_service.repository.ProductRepository;
import com.example.product_service.repository.entity.Product;
import com.example.product_service.request.ProductCreateRequest;
import com.example.product_service.service.IProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRepositoryServiceImpl implements IProductRepositoryService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(ProductCreateRequest productCreateRequest) {
        Product product = new Product();
        product.setProductName(productCreateRequest.getProductName());
        product.setQuantity(productCreateRequest.getQuantity());
        product.setPrice(productCreateRequest.getPrice());
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
