package com.smartjob.creacionusuarios.domain.service;

import com.smartjob.creacionusuarios.domain.model.Telefono;
import com.smartjob.creacionusuarios.domain.model.Usuario;
import com.smartjob.creacionusuarios.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService service;

    @MockitoBean
    private UsuarioRepository repository;

    @Test
    public void testCrearUsuarioService() {

        Telefono telefono = new Telefono(1L, "7789123", "11", "54");
        UUID id = UUID.randomUUID();
        Usuario modelo = new Usuario(id, "Carlos Perez", "carlos.perez@gmail.com", "hunter3", telefono);

        Usuario modeloResponse = new Usuario(true, "token", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "hunter3", "carlos.perez@gmail.com", "Carlos Perez", id, telefono);

        when(repository.save(modelo)).thenReturn(modeloResponse);

        Usuario usuarioCreado = service.create(modelo);
        assertEquals(modelo.getEmail(), usuarioCreado.getEmail());
    }
}
