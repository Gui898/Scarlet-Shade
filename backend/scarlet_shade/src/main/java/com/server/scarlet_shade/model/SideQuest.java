package com.server.scarlet_shade.model;

import jakarta.persistence.*;
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

    @Column(name = "status_side_quest", nullable = false)
    private String statusSideQuest;

    //private Slot slot

    public SideQuest(String nameSideQuest, String statusSideQuest) {
        this.nameSideQuest = nameSideQuest;
        this.statusSideQuest = statusSideQuest;
    }
}
