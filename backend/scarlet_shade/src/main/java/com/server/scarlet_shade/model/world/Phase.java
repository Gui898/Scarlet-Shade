package com.server.scarlet_shade.model.world;

import com.server.scarlet_shade.utils.enumerator.PhaseName;
import com.server.scarlet_shade.utils.enumerator.StatusPhase;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "name_phase", nullable = false)
    private PhaseName namePhase = PhaseName.CHAPTER_ONE_PHASE_ONE;

    @ManyToOne
    @JoinColumn(name = "id_world_progress", nullable = false)
    private WorldProgress worldProgress;

    public Phase(StatusPhase statusPhase, PhaseName namePhase, WorldProgress worldProgress) {
        this.statusPhase = statusPhase;
        this.namePhase = namePhase;
        this.worldProgress = worldProgress;
    }

    public Phase(StatusPhase statusPhase, PhaseName namePhase) {
        this.statusPhase = statusPhase;
        this.namePhase = namePhase;
    }
}