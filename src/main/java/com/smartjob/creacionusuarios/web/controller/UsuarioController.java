package com.smartjob.creacionusuarios.web.controller;

import com.smartjob.creacionusuarios.web.dto.UsuarioRequest;
import com.smartjob.creacionusuarios.web.dto.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class UsuarioController {
    @PostMapping("/usuarios")
    public UsuarioResponse crearUsuario(@Valid @RequestBody UsuarioRequest request) {
        String dateString = "2025-05-24 20:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fecha = LocalDateTime.parse(dateString, formatter);
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30";
        UUID id = UUID.fromString("37bdaee2-184e-430d-9629-19b60e45225f");

        return new UsuarioResponse(id, fecha, fecha, fecha, token, true);
    }
}
