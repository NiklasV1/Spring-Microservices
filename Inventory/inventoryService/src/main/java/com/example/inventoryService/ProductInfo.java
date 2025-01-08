package com.example.inventoryService;

import java.util.UUID;

public record ProductInfo(UUID id, String name, long amount, double price) {
}
