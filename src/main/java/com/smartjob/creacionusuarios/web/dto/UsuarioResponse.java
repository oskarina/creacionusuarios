package com.smartjob.creacionusuarios.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        LocalDateTime created,
        LocalDateTime modified,
        LocalDateTime lastLogin,
        String token,
        boolean isActive
) {
}
