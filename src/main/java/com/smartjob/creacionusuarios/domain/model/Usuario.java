package com.smartjob.creacionusuarios.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Usuario {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final List<Telefono> telefonos;
    private final LocalDateTime created;
    private final LocalDateTime modified;
    private final LocalDateTime lastLogin;
    private final String token;
    private final Boolean isActive;

    public Usuario(Long id, String name, String email, String password, Telefono... telefonos) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.telefonos = List.of(telefonos);
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        this.lastLogin = null;
        this.token = null;
        this.isActive = true;
    }

    public Usuario(Boolean isActive, String token, LocalDateTime lastLogin, LocalDateTime modified, LocalDateTime created, String password, String email, String name, Long id, Telefono... telefonos) {
        this.isActive = isActive;
        this.token = token;
        this.lastLogin = lastLogin;
        this.modified = modified;
        this.created = created;
        this.password = password;
        this.email = email;
        this.name = name;
        this.id = id;
        this.telefonos = List.of(telefonos);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public String getToken() {
        return token;
    }

    public Boolean getActive() {
        return isActive;
    }
}
