package com.server.scarlet_shade.model.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "yokai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Yokai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_yokai")
    private Long id;

    @Column(name = "name_yokai", nullable = false)
    private String nameYokai;

    @ManyToOne
    @JoinColumn(name = "id_slot")
    private Slot slot;

    public Yokai(String nameYokai, Slot slot) {
        this.nameYokai = nameYokai;
        this.slot = slot;
    }
}
