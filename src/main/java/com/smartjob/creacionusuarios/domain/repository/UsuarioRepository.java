package com.smartjob.creacionusuarios.domain.repository;

import com.smartjob.creacionusuarios.persistence.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
