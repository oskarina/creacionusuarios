package com.smartjob.creacionusuarios.persistence;

import com.smartjob.creacionusuarios.persistence.dto.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioSpringRepository extends JpaRepository<UsuarioEntity, UUID> {
    Optional<UsuarioEntity> findTopByOrderByRestIdDesc();
}