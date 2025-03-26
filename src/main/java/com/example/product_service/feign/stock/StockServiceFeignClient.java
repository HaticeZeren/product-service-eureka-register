package com.example.product_service.feign.stock;


import com.example.product_service.feign.stock.model.Stock;
import com.example.product_service.request.StockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "stock-service", url = "http://zuul-edge-server:8762/stock")
public interface StockServiceFeignClient {

   @PostMapping(value = "/save")
   Stock save(@RequestBody StockRequest stockRequest);

   @GetMapping("/findAllStock")
   List<Stock> findAllStock();

   @GetMapping(value = "/findStockByProductId")
   Stock findStockByProductId(@RequestParam("productId") Long productId);

   }
