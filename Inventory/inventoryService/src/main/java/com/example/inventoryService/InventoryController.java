package com.example.inventoryService;

import com.example.inventoryService.records.ProductAmountRecord;
import com.example.inventoryService.records.ProductCreationRecord;
import com.example.inventoryService.records.ProductPriceRecord;
import com.example.inventoryService.records.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class InventoryController {

    @Autowired
    private InventoryManager inventoryManager;

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String handleUnexpectedExceptions() {
        return "Unexpected Server Exception!";
    }

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

    // NOTE: Get price of product
    @GetMapping("/getPrice")
    public double getPrice(@RequestParam UUID id) {
        return inventoryManager.getPrice(id);
    }

    //NOTE: Set price of product
    @PostMapping("/setPrice")
    public UUID setPrice(@RequestBody ProductPriceRecord input) {
        return inventoryManager.setPrice(input.id(), input.price());
    }
}
