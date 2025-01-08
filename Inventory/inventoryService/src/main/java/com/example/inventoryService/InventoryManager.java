package com.example.inventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public long getAmount(UUID id) {
        Product product = productRepository.getReferenceById(id);
        return product.getAmount();
    }

    public UUID setAmount(UUID id, long amount) {
        Product product = productRepository.getReferenceById(id);
        product.setAmount(amount);
        productRepository.save(product);
        return product.getId();
    }

    public UUID registerProduct(String name, double price) {
        ensureMaxStringLength(name, 100);
        ensurePositivePrice(price);
        Product product = new Product(name, price);
        productRepository.save(product);
        return product.getId();
    }

    public void ensurePositivePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can not be negative!");
        }
    }

    public void ensureMaxStringLength(String input, int maxLength) {
        if (input.length() > maxLength) {
            throw new IllegalArgumentException("String is too long!");
        }
    }
}
