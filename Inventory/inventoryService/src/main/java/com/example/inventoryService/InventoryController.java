package com.example.inventoryService;

import com.example.inventoryService.records.ProductAmountRecord;
import com.example.inventoryService.records.ProductCreationRecord;
import com.example.inventoryService.records.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getAmount")
    public long getAmount(@RequestParam UUID id) {
        return inventoryManager.getAmount(id);
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

    // NOTE: Unregister products with amount == 0
    @PostMapping("/unregisterProduct")
    public UUID unregisterProduct(@RequestParam UUID id) {
        return inventoryManager.unregisterProduct(id);
    }
}
