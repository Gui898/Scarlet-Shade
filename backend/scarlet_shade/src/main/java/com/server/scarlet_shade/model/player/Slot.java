package com.server.scarlet_shade.model.player;

import java.util.ArrayList;
import java.util.List;

import com.server.scarlet_shade.model.User;
import com.server.scarlet_shade.model.item.EquipedItem;
import com.server.scarlet_shade.model.item.InventoryItem;
import com.server.scarlet_shade.model.item.UsableItem;
import com.server.scarlet_shade.model.world.SideQuest;
import com.server.scarlet_shade.model.world.WorldProgress;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "slot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Slot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_slot")
    private Long id;

    @Column(name = "number_slot", nullable = false)
    private Integer numberSlot;

    @Column(name = "game_completed", nullable = false)
    private Boolean gameCompleted = false;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne(mappedBy = "slot", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private WorldProgress worldProgress;

    // Possibilidade de ter PERSIST por causa do equipamento básico; 
    @OneToOne(mappedBy = "slot", cascade = CascadeType.REMOVE)
    private EquipedItem equipedItem;

    @OneToOne(mappedBy = "slot", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Player player;

    @OneToMany(mappedBy = "slot", cascade = CascadeType.REMOVE)
    private List<InventoryItem> inventoryItem = new ArrayList<>();

    @OneToMany(mappedBy = "slot", cascade = CascadeType.REMOVE)
    private List<UsableItem> usableItem = new ArrayList<>();

    @OneToMany(mappedBy = "slot", cascade = CascadeType.REMOVE)
    private List<SideQuest> sideQuests = new ArrayList<>();

    @OneToMany(mappedBy = "slot", cascade = CascadeType.REMOVE)
    private List<Yokai> yokais = new ArrayList<>();

    @OneToMany(mappedBy = "slot", cascade = CascadeType.REMOVE)
    private List<Jade> jades = new ArrayList<>();

    public Slot(Integer numberSlot, Boolean gameCompleted, User user, Player player) {
        this.numberSlot = numberSlot;
        this.gameCompleted = gameCompleted;
        this.user = user;
        this.player = player;
    }

    public Slot(Integer numberSlot, Boolean gameCompleted) {
        this.numberSlot = numberSlot;
        this.gameCompleted = gameCompleted;
    }
}