package com.server.scarlet_shade.model.item;

import com.server.scarlet_shade.model.player.Slot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usable_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsableItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usable_item")
    private Long id;

    @Column(name = "number_item", nullable = false)
    private Integer numberItem;

    @ManyToOne
    @JoinColumn(name = "id_slot", nullable = false)
    private Slot slot;

    @OneToOne
    @JoinColumn(name = "id_inventory_item", nullable = false)
    private InventoryItem inventoryItem;

    public UsableItem(Integer numberItem, Slot slot, InventoryItem inventoryItem) {
        this.numberItem = numberItem;
        this.slot = slot;
        this.inventoryItem = inventoryItem;
    }

    public UsableItem(Integer numberItem) {
        this.numberItem = numberItem;
    }
}