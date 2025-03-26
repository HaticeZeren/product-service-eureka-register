package com.example.product_service.service.impl;

import com.example.product_service.feign.stock.StockServiceFeignClient;
import com.example.product_service.feign.stock.model.Stock;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.repository.entity.Product;
import com.example.product_service.request.ProductCreateRequest;
import com.example.product_service.request.StockRequest;
import com.example.product_service.service.IProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRepositoryServiceImpl implements IProductRepositoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockServiceFeignClient stockServiceFeignClient;

    @Override
    public Product createProduct(ProductCreateRequest productCreateRequest) {
        Product product = new Product();
        product.setProductName(productCreateRequest.getProductName());
        product.setQuantity(productCreateRequest.getQuantity());
        product.setPrice(productCreateRequest.getPrice());
        Product saveProduct =  productRepository.save(product);
        //ürün stoğa kayıt edildi.(FeignClient kullanım örneğidir)
        Stock stock = stockServiceFeignClient.save(StockRequest.StockRequestMapper(saveProduct));
        return saveProduct;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
