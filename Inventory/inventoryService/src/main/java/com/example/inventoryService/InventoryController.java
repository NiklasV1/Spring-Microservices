package com.example.inventoryService;

import com.example.inventoryService.records.ProductAmountRecord;
import com.example.inventoryService.records.ProductCreationRecord;
import com.example.inventoryService.records.ProductRecord;
import com.example.inventoryService.records.ProductUUIDRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class InventoryController {

    @Autowired
    private InventoryManager inventoryManager;

    // NOTE: Health check
    @GetMapping("/health")
    public String healthCheck() {
        return "Service running!";
    }

    // NOTE: Get all products
    @GetMapping("/getProducts")
    public List<ProductRecord> getAllProducts() {
        return inventoryManager.getAllProducts();
    }

    // NOTE: Get amount for product by UUID
    @GetMapping(value = "/getAmount", consumes = "application/json", produces = "application/json")
    public long getAmount(@RequestBody ProductUUIDRecord input) {
        return inventoryManager.getAmount(input.id());
    }

    // NOTE: Set amount for product by UUID
    @PostMapping("/setAmount")
    public UUID setAmount(@RequestBody ProductAmountRecord input) {
        return inventoryManager.setAmount(input.id(), input.amount());
    }

    // NOTE: Register new product
    @PostMapping(value = "/registerProduct", consumes = "application/json", produces = "application/json")
    public UUID registerProduct(@RequestBody ProductCreationRecord input) {
        return inventoryManager.registerProduct(input.name(), input.price());
    }

    @PostMapping(value = "/unregisterProduct", consumes = "application/json", produces = "application/json")
    public UUID unregisterProduct(@RequestBody ProductUUIDRecord input) {
        return inventoryManager.unregisterProduct(input.id());
    }
}
