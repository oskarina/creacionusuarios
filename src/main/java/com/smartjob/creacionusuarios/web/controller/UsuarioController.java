package com.smartjob.creacionusuarios.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @PostMapping("/usuarios")
    public String crearUsuario(){
        return "Usuario creado!";
    }
}
