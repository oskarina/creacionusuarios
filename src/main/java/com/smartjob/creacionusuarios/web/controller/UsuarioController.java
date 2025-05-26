package com.smartjob.creacionusuarios.web.controller;

import com.smartjob.creacionusuarios.domain.model.Telefono;
import com.smartjob.creacionusuarios.domain.model.Usuario;
import com.smartjob.creacionusuarios.domain.service.UsuarioService;
import com.smartjob.creacionusuarios.web.dto.UsuarioRequest;
import com.smartjob.creacionusuarios.web.dto.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/usuarios")
    public UsuarioResponse crearUsuario(@Valid @RequestBody UsuarioRequest request) {
        var telefonos = request.phones().stream().map(phoneRequest -> new Telefono(
                phoneRequest.number(),
                phoneRequest.cityCode(),
                phoneRequest.countryCode()
        )).toList().toArray(new Telefono[0]);
        var modelo = new Usuario(
                service.generateId(),
                request.name(),
                request.email(),
                request.password(),
                telefonos
        );

        var usuarioCreado = service.create(modelo);

        return new UsuarioResponse(
                usuarioCreado.getId(),
                usuarioCreado.getCreated(),
                usuarioCreado.getModified(),
                usuarioCreado.getLastLogin(),
                usuarioCreado.getToken(),
                usuarioCreado.getActive()
        );
    }
}
