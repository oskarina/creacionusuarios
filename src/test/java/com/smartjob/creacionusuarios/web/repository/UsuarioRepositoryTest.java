package com.smartjob.creacionusuarios.web.repository;

import com.smartjob.creacionusuarios.domain.repository.UsuarioRepository;
import com.smartjob.creacionusuarios.persistence.dto.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED) // permite que flush lance el error
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void limpiarBD() {
        usuarioRepository.deleteAll();
    }

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

    @Test
    public void testGuardarUsuarioDuplicado() {
        // given
        Usuario usuario = new Usuario();
        usuario.setName("Juan Rodriguez");
        usuario.setEmail("Juan@rodriguez.org");
        usuario.setPassword("hunter2");

        Usuario duplicado = new Usuario();
        usuario.setName("Carlos Perez");
        usuario.setEmail("Juan@rodriguez.org");
        usuario.setPassword("hunter2");

        usuarioRepository.save(usuario);

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            usuarioRepository.save(duplicado);
        });

        String expectedMessage = "EMAIL";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
