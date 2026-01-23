package com.server.scarlet_shade.model.world;

import java.util.ArrayList;
import java.util.List;

import com.server.scarlet_shade.model.player.Slot;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "world_progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorldProgress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_world_progress")
    private Long id;

    @Column(name = "current_phase", nullable = false)
    private String currentPhase;

    @OneToOne
    @JoinColumn(name = "id_slot", nullable = false)
    private Slot slot;

    @OneToMany(mappedBy = "worldProgress", cascade = CascadeType.REMOVE)
    private List<Phase> phases = new ArrayList<>();

    public WorldProgress(String currentPhase, Slot slot) {
        this.currentPhase = currentPhase;
        this.slot = slot;
    }

    public WorldProgress(String currentPhase) {
        this.currentPhase = currentPhase;
    }
}