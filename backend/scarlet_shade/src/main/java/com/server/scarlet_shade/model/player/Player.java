package com.server.scarlet_shade.model.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    private Integer damage = 5;

    @Column(name = "speed", nullable = false)
    private Integer speed = 5;

    @Column(name = "life", nullable = false)
    private Integer life = 100;

    @Column(name = "max_life", nullable = false)
    private Integer maxLife = 100;

    @Column(name = "money", nullable = false)
    private Integer money = 0;

    @Column(name = "element")
    private String element;

    @Column(name = "current_yokai")
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

    public Player(Integer damage, Integer speed, Integer life, Integer maxLife, Integer money, String element, String currentYokai) {
        this.damage = damage;
        this.speed = speed;
        this.life = life;
        this.maxLife = maxLife;
        this.money = money;
        this.element = element;
        this.currentYokai = currentYokai;
    }
}