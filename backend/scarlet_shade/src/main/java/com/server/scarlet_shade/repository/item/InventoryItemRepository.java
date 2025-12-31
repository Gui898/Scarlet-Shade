package com.server.scarlet_shade.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.scarlet_shade.model.item.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    
}