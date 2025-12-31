package com.server.scarlet_shade.model.item;

import com.server.scarlet_shade.model.player.Slot;
import com.server.scarlet_shade.utils.enumerator.TypeItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "inventory_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventory_item")
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_item", nullable = false)
    private TypeItem typeItem = TypeItem.UTILITY;

    @Column(name = "name_item", nullable = false)
    private String nameItem;

    @ManyToOne
    @JoinColumn(name = "id_slot")
    private Slot slot;

    @OneToOne(mappedBy = "inventoryItem")
    private UsableItem usableItem;

    public InventoryItem(Integer quantity, TypeItem typeItem, String nameItem, Slot slot) {
        this.quantity = quantity;
        this.typeItem = typeItem;
        this.nameItem = nameItem;
        this.slot = slot;
    }

    public InventoryItem(Integer quantity, TypeItem typeItem, String nameItem) {
        this.quantity = quantity;
        this.typeItem = typeItem;
        this.nameItem = nameItem;
    }
}