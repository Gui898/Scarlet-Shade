package com.server.scarlet_shade.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.server.scarlet_shade.model.controls.GamepadControls;
import com.server.scarlet_shade.model.controls.KeyboardControls;
import com.server.scarlet_shade.model.player.Slot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_user", nullable = false)
    private String password;

    @Column(name = "soundtrack", nullable = false)
    private BigDecimal soundtrack = BigDecimal.valueOf(0.3);

    @Column(name = "sound_effect", nullable = false)
    private BigDecimal soundEffects = BigDecimal.valueOf(0.5);

    @OneToOne(mappedBy = "user")
    private KeyboardControls keyboardControls;

    @OneToOne(mappedBy = "user")
    private GamepadControls gamepadControls;

    @OneToMany(mappedBy = "user")
    private List<Slot> slots = new ArrayList<>();

    public User(String username, String email, String password, BigDecimal soundtrack, BigDecimal soundEffects, KeyboardControls keyboardControls, GamepadControls gamepadControls) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.soundtrack = soundtrack;
        this.soundEffects = soundEffects;
        this.keyboardControls = keyboardControls;
        this.gamepadControls = gamepadControls;
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
}