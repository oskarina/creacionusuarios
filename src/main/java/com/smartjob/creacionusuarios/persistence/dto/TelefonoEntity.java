package com.smartjob.creacionusuarios.persistence.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "telefonos")
public class TelefonoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El numero es obligatorio")
    @Column(nullable = false)
    private String number;

    @NotBlank(message = "El codigo de la ciudad es obligatorio")
    @Column(nullable = false)
    private String cityCode;

    @NotBlank(message = "El codigo del pais es obligatorio")
    @Column(nullable = false)
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    public TelefonoEntity() {
    }

    public TelefonoEntity(Long id, String number, String cityCode, String countryCode, UsuarioEntity usuario) {
        this.id = id;
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "El numero es obligatorio") String getNumber() {
        return number;
    }

    public void setNumber(@NotBlank(message = "El numero es obligatorio") String number) {
        this.number = number;
    }

    public @NotBlank(message = "El codigo de la ciudad es obligatorio") String getCityCode() {
        return cityCode;
    }

    public void setCityCode(@NotBlank(message = "El codigo de la ciudad es obligatorio") String cityCode) {
        this.cityCode = cityCode;
    }

    public @NotBlank(message = "El codigo del pais es obligatorio") String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(@NotBlank(message = "El codigo del pais es obligatorio") String countryCode) {
        this.countryCode = countryCode;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}

