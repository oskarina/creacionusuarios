package com.smartjob.creacionusuarios.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record UsuarioRequest(
        String name,
        @NotEmpty @Email String email,
        String password,
        List<PhoneRequest> phones

) {
}
