package com.server.scarlet_shade.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.scarlet_shade.model.item.EquipedItem;

public interface EquipedItemRepository extends JpaRepository<EquipedItem, Long>{
    
}