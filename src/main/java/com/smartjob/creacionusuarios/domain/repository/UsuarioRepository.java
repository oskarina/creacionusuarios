package com.smartjob.creacionusuarios.domain.repository;

import com.smartjob.creacionusuarios.domain.model.Usuario;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);

    Long topId();
}
