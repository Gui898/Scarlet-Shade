package com.server.scarlet_shade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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
    private BigDecimal soundtrack;

    @Column(name = "sound_effect", nullable = false)
    private BigDecimal soundEffects;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private KeyboardControls keyboardControls;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private GamepadControls gamepadControls;

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
        this.soundtrack = BigDecimal.valueOf(0.3);
        this.soundEffects = BigDecimal.valueOf(0.5);
        this.keyboardControls = new KeyboardControls();
        this.gamepadControls = new GamepadControls();
    }

}
