package com.example.lostnfoundrevamped.entities;

import com.example.lostnfoundrevamped.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "realname", nullable = false, length = 64)
    private String realname;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 16)
    private UserRole role;

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    private Set<ItemEntity> itemEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    private Set<MessageEntity> messageEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)
    private Set<AlertEntity> alertEntities = new LinkedHashSet<>();
}