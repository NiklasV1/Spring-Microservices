package com.example.inventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryManager inventoryManager;

    @GetMapping("/getProducts")
    public List<ProductInfo> getAllProducts() {
        return inventoryManager.getAllProducts();
    }
}
