package com.smartjob.creacionusuarios.web.dto;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Long id,
        LocalDateTime created,
        LocalDateTime modified,
        LocalDateTime lastLogin,
        String token,
        boolean isActive
) {
}
