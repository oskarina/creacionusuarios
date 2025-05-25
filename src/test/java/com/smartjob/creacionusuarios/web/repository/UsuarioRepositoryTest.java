package com.smartjob.creacionusuarios.web.repository;

import com.smartjob.creacionusuarios.domain.repository.UsuarioRepository;
import com.smartjob.creacionusuarios.persistence.dto.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarUsuario() {
        // given
        Usuario usuario = new Usuario();
        usuario.setName("Juan Rodriguez");
        usuario.setEmail("Juan@rodriguez.org");
        usuario.setPassword("hunter2");

        usuarioRepository.save(usuario);

        // when
        Iterable<Usuario> encontrados = usuarioRepository.findAll();
        Optional<Usuario> encontrado = StreamSupport.stream(encontrados.spliterator(), false).findFirst();

        // then
        assertThat(encontrado).isPresent();
        assertThat(encontrado.orElseThrow().getName()).isEqualTo("Juan Rodriguez");
    }
}
