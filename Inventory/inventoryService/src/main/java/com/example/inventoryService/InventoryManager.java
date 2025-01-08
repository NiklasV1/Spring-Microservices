package com.example.inventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryManager {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductInfo> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductInfo> productInfos = new ArrayList<>();
        for (Product product : products) {
            productInfos.add(new ProductInfo(product.getId(), product.getName(), product.getAmount(), product.getPrice()));
        }
        return productInfos;
    }
}
