package com.smartjob.creacionusuarios.domain.repository;

import com.smartjob.creacionusuarios.persistence.UsuarioSpringRepository;
import com.smartjob.creacionusuarios.persistence.dto.UsuarioEntity;
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
public class UsuarioSpringRepositoryTest {

    @Autowired
    private UsuarioSpringRepository usuarioSpringRepository;

    @BeforeEach
    void limpiarBD() {
        usuarioSpringRepository.deleteAll();
    }

    @Test
    public void testGuardarUsuario() {
        // given
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setName("Juan Rodriguez");
        usuario.setEmail("Juan@rodriguez.org");
        usuario.setToken("TOKEN");
        usuario.setRestId(1L);

        usuarioSpringRepository.save(usuario);

        // when
        Iterable<UsuarioEntity> encontrados = usuarioSpringRepository.findAll();
        Optional<UsuarioEntity> encontrado = StreamSupport.stream(encontrados.spliterator(), false).findFirst();

        // then
        assertThat(encontrado).isPresent();
        assertThat(encontrado.orElseThrow().getName()).isEqualTo("Juan Rodriguez");
    }

    @Test
    public void testGuardarUsuarioDuplicado() {
        // given
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setName("Juan Rodriguez");
        usuario.setEmail("Juan@rodriguez.org");
        usuario.setToken("TOKEN");
        usuario.setRestId(1L);

        UsuarioEntity duplicado = new UsuarioEntity();
        duplicado.setName("Carlos Perez");
        duplicado.setEmail("Juan@rodriguez.org");
        duplicado.setToken("TOKEN");
        duplicado.setRestId(1L);
        usuarioSpringRepository.save(usuario);

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            usuarioSpringRepository.save(duplicado);
        });

        String expectedMessage = "email";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
