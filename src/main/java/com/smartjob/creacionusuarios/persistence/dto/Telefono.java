package com.smartjob.creacionusuarios.persistence.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "telefonos")
public class Telefono {
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
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
    }
}

