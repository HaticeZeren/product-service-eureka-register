package com.example.product_service.request;

import com.example.product_service.repository.entity.Product;

public class StockRequest {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;

    public static StockRequest StockRequestMapper(Product product) {
        StockRequest stockRequest = new StockRequest();
        stockRequest.setProductId(product.getProductId());
        stockRequest.setProductName(product.getProductName());
        stockRequest.setQuantity(product.getQuantity());
        stockRequest.setPrice(product.getPrice());
        return stockRequest;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
