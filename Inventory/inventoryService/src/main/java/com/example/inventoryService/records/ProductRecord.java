package com.example.inventoryService.records;

import java.util.UUID;

public record ProductRecord(UUID id, String name, long amount, double price) {
}
