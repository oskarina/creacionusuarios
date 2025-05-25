package com.smartjob.creacionusuarios.web.repository;

import com.smartjob.creacionusuarios.persistence.dto.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private CrudRepository<Usuario, Long> usuarioRepository;

    @Test
    public void testGuardarUsuario() {
        // given
        Usuario usuario = new Usuario();
        usuario.setName("Carlos Pérez");
        usuario.setEmail("carlos@example.com");
        usuario.setPassword("hunter2");

        usuarioRepository.save(usuario);

        // when
        Iterable<Usuario> encontrados = usuarioRepository.findAll();
        Optional<Usuario> encontrado = StreamSupport.stream(encontrados.spliterator(), false).findFirst();

        // then
        assertThat(encontrado).isPresent();
        assertThat(encontrado.orElseThrow().getName()).isEqualTo("Carlos Pérez");
    }
}
