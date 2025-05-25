package com.smartjob.creacionusuarios.web.dto;

import java.util.List;

public record UsuarioRequest(
        String name,
        String email,
        String password,
        List<PhoneRequest> phones

) {
}
