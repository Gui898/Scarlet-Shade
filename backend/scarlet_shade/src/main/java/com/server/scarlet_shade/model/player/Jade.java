package com.server.scarlet_shade.model.player;

import jakarta.persistence.*;
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
}
