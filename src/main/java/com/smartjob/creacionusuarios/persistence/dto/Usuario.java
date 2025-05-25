package com.smartjob.creacionusuarios.persistence.dto;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefono> telefonos = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(UUID id, String name, String email, String password, List<Telefono> telefonos) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.telefonos = telefonos;
    }

    public void addTelefono(Telefono telefono) {
        telefonos.add(telefono);
        telefono.setUsuario(this);
    }

    public void removeTelefono(Telefono telefono) {
        telefonos.remove(telefono);
        telefono.setUsuario(null);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefonos=" + telefonos +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }
}
