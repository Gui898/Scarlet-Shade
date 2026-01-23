package com.server.scarlet_shade.model.item;

import com.server.scarlet_shade.model.player.Slot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equiped_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipedItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equiped_item")
    private Long id;

    @Column(name = "armor" , nullable = false)
    private String armor;

    @Column(name = "sword", nullable = false)
    private String sword;

    @OneToOne
    @JoinColumn(name = "id_slot", nullable = false)
    private Slot slot;

    public EquipedItem(String armor, String sword) {
        this.armor = armor;
        this.sword = sword;
    }
}