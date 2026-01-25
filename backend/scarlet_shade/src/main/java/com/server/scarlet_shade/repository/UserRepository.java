package com.server.scarlet_shade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.server.scarlet_shade.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    User findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);

    boolean existsByUsernameOrEmailAndIdNot(String username, String email, Long id);
}