package com.server.scarlet_shade.model.world;

import com.server.scarlet_shade.model.player.Slot;
import com.server.scarlet_shade.utils.enumerator.StatusSideQuest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "side_quest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SideQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_side_quest")
    private Long id;

    @Column(name = "name_side_quest", nullable = false)
    private String nameSideQuest;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_side_quest", nullable = false)
    private StatusSideQuest statusSideQuest = StatusSideQuest.PROGRESS;

    @ManyToOne
    @JoinColumn(name = "id_slot")
    private Slot slot;

    public SideQuest(String nameSideQuest, StatusSideQuest statusSideQuest, Slot slot) {
        this.nameSideQuest = nameSideQuest;
        this.statusSideQuest = statusSideQuest;
        this.slot = slot;
    }

    public SideQuest(String nameSideQuest, StatusSideQuest statusSideQuest) {
        this.nameSideQuest = nameSideQuest;
        this.statusSideQuest = statusSideQuest;
    }
}