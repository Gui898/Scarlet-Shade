package com.server.scarlet_shade.model.world;

import com.server.scarlet_shade.model.player.Slot;
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

    @ManyToOne
    @JoinColumn(name = "id_slot")
    private Slot slot;

    public SideQuest(String nameSideQuest, String statusSideQuest, Slot slot) {
        this.nameSideQuest = nameSideQuest;
        this.statusSideQuest = statusSideQuest;
        this.slot = slot;
    }
}
