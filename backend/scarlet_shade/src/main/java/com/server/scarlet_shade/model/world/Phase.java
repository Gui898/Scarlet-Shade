package com.server.scarlet_shade.model.world;

import com.server.scarlet_shade.utils.StatusPhase;

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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "phases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phase")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_phase", nullable = false)
    private StatusPhase statusPhase = StatusPhase.DISCOVERED;

    @Column(name = "name_phase", nullable = false)
    private String namePhase;

    @ManyToOne
    @JoinColumn(name = "id_world_progress")
    private WorldProgress worldProgress;

    public Phase(StatusPhase statusPhase, String namePhase, WorldProgress worldProgress) {
        this.statusPhase = statusPhase;
        this.namePhase = namePhase;
        this.worldProgress = worldProgress;
    }

    public Phase(StatusPhase statusPhase, String namePhase) {
        this.statusPhase = statusPhase;
        this.namePhase = namePhase;
    }
}