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

    public User(String username, String email, String password, BigDecimal soundtrack, BigDecimal soundEffects){
        this.username = username;
        this.email = email;
        this.password = password;
        this.soundtrack = soundtrack;
        this.soundEffects = soundEffects;
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.soundtrack = BigDecimal.valueOf(0.3);
        this.soundEffects = BigDecimal.valueOf(0.5);
    }

}
