package com.smartjob.creacionusuarios.persistence;

import com.smartjob.creacionusuarios.domain.model.Telefono;
import com.smartjob.creacionusuarios.domain.model.Usuario;
import com.smartjob.creacionusuarios.persistence.dto.TelefonoEntity;
import com.smartjob.creacionusuarios.persistence.dto.UsuarioEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UsuarioRepositoryImplTest {

    @Mock
    private UsuarioSpringRepository springRepository;

    @InjectMocks
    private UsuarioRepositoryImpl repository;

    public UsuarioRepositoryImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUsuario() {
        // Arrange
        UUID id = UUID.randomUUID();
        Telefono telefono = new Telefono(1L, "7789123", "11", "54");
        Usuario usuarioModel = new Usuario(id, "Carlos Perez", "carlos.perez@gmail.com", "hunter3", telefono);

        UsuarioEntity usuarioEntity = new UsuarioEntity(id, "Carlos Perez", "carlos.perez@gmail.com", "hunter3");
        TelefonoEntity telefonoEntity = new TelefonoEntity(1L, "7789123", "11", "54", usuarioEntity);
        usuarioEntity.setTelefonos(List.of(telefonoEntity));

        // Simula lo que devuelve el repositorio al guardar
        usuarioEntity.setActive(true);
        usuarioEntity.setToken("mock-token");
        usuarioEntity.setCreated(LocalDateTime.now());
        usuarioEntity.setModified(LocalDateTime.now());
        usuarioEntity.setLastLogin(LocalDateTime.now());

        when(springRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        // Act
        Usuario resultado = repository.save(usuarioModel);

        // Assert
        assertEquals(usuarioModel.getEmail(), resultado.getEmail());
        assertEquals(usuarioModel.getName(), resultado.getName());
        assertEquals(usuarioModel.getPassword(), resultado.getPassword());
        assertEquals(1, resultado.getTelefonos().size());
        assertEquals("11", resultado.getTelefonos().get(0).getCityCode());
    }
}
