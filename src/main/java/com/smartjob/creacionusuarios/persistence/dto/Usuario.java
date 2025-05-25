package com.smartjob.creacionusuarios.persistence.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe contener un formato valido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "El password es obligatorio")
    @Size(min = 6, message = "El password debe tener por lo menos 6 caracteres")
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

    public @NotBlank(message = "El nombre es obligatorio") @Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "El nombre es obligatorio") @Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres") String name) {
        this.name = name;
    }

    public @NotBlank(message = "El email es obligatorio") @Email(message = "El email debe contener un formato valido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "El email es obligatorio") @Email(message = "El email debe contener un formato valido") String email) {
        this.email = email;
    }

    public @NotBlank(message = "El password es obligatorio") @Size(min = 6, message = "El password debe tener por lo menos 6 caracteres") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "El password es obligatorio") @Size(min = 6, message = "El password debe tener por lo menos 6 caracteres") String password) {
        this.password = password;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }
}
