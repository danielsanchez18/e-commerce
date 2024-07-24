package com.dsac.ecommer_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUserRole;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne
    private Role role;

    public UUID getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(UUID idUserRole) {
        this.idUserRole = idUserRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
