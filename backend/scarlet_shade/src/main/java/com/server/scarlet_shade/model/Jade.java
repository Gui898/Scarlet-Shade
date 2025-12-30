package com.server.scarlet_shade.model;

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

    //private Slot slot;


    public Jade(String nameJade) {
        this.nameJade = nameJade;
    }
}
