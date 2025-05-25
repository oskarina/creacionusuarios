package com.smartjob.creacionusuarios.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record UsuarioRequest(
        String name,
        @NotEmpty @Email String email,
        @NotBlank
        @Pattern(
                regexp = "^[a-zA-Z]{6}\\d$",
                message = "La clave es invalida, deben ser 6 letras y 1 numero"
        )
        String password,
        List<PhoneRequest> phones

) {
}
