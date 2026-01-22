package com.server.scarlet_shade.model.controls;

import com.server.scarlet_shade.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Controls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_control")
    private Long id;

    @Column(name = "move_up", nullable = false)
    private String moveUp;

    @Column(name = "move_down", nullable = false)
    private String moveDown;

    @Column(name = "move_left", nullable = false)
    private String moveLeft;

    @Column(name = "move_right", nullable = false)
    private String moveRight;

    @Column(name = "jump", nullable = false)
    private String jump;

    @Column(name = "dash", nullable = false)
    private String dash;

    @Column(name = "crouch", nullable = false)
    private String crouch;

    @Column(name = "attack", nullable = false)
    private String attack;

    @Column(name = "spin_attack", nullable = false)
    private String spinAttack;

    @Column(name = "especial_move_one", nullable = false)
    private String especialMoveOne;

    @Column(name = "especial_move_two", nullable = false)
    private String especialMoveTwo;

    @Column(name = "menu_access", nullable = false)
    private String menuAccess;

    @Column(name = "select_itens", nullable = false)
    private String selectItem;

    @Column(name = "use_item", nullable = false)
    private String useItem;

    @OneToOne
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User user;

    public Controls(String moveUp, String moveDown, String moveLeft, String moveRight, String jump, String dash, String crouch, String attack, String spinAttack, String especialMoveOne, String especialMoveTwo, String menuAccess, String selectItem, String useItem, User user) {
        this.moveUp = moveUp;
        this.moveDown = moveDown;
        this.moveLeft = moveLeft;
        this.moveRight = moveRight;
        this.jump = jump;
        this.dash = dash;
        this.crouch = crouch;
        this.attack = attack;
        this.spinAttack = spinAttack;
        this.especialMoveOne = especialMoveOne;
        this.especialMoveTwo = especialMoveTwo;
        this.menuAccess = menuAccess;
        this.selectItem = selectItem;
        this.useItem = useItem;
        this.user = user;
    }

    public Controls(String moveUp, String moveDown, String moveLeft, String moveRight, String jump, String dash, String crouch, String attack, String spinAttack, String especialMoveOne, String especialMoveTwo, String menuAccess, String selectItem, String useItem) {
        this.moveUp = moveUp;
        this.moveDown = moveDown;
        this.moveLeft = moveLeft;
        this.moveRight = moveRight;
        this.jump = jump;
        this.dash = dash;
        this.crouch = crouch;
        this.attack = attack;
        this.spinAttack = spinAttack;
        this.especialMoveOne = especialMoveOne;
        this.especialMoveTwo = especialMoveTwo;
        this.menuAccess = menuAccess;
        this.selectItem = selectItem;
        this.useItem = useItem;
    }
}