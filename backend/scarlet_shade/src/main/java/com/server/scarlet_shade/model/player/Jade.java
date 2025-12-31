package com.server.scarlet_shade.model.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "jade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jade")
    private Long id;

    @Column(name = "name_jade", nullable = false)
    private String nameJade;

    @ManyToOne
    @JoinColumn(name = "id_slot")
    private Slot slot;

    public Jade(String nameJade, Slot slot) {
        this.nameJade = nameJade;
        this.slot = slot;
    }

    public Jade(String nameJade) {
        this.nameJade = nameJade;
    }
}
