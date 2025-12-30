package com.server.scarlet_shade.model.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "player")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_player")
    private Long id;

    @Column(name = "damage", nullable = false)
    private Integer damage;

    @Column(name = "speed", nullable = false)
    private Integer speed;

    @Column(name = "life", nullable = false)
    private Integer life;

    @Column(name = "max_life", nullable = false)
    private Integer maxLife;

    @Column(name = "money", nullable = false)
    private Integer money;

    @Column(name = "element", nullable = false)
    private String element;

    @Column(name = "current_yokai", nullable = false)
    private String currentYokai;

    @OneToOne
    @JoinColumn(name = "id_slot")
    private Slot slot;

    public Player(Integer damage, Integer speed, Integer life, Integer maxLife, Integer money, String element, String currentYokai, Slot slot) {
        this.damage = damage;
        this.speed = speed;
        this.life = life;
        this.maxLife = maxLife;
        this.money = money;
        this.element = element;
        this.currentYokai = currentYokai;
        this.slot = slot;
    }
}
