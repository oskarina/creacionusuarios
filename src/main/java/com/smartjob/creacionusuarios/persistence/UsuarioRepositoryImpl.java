package com.smartjob.creacionusuarios.persistence;

import com.smartjob.creacionusuarios.domain.model.Telefono;
import com.smartjob.creacionusuarios.domain.model.Usuario;
import com.smartjob.creacionusuarios.domain.repository.UsuarioRepository;
import com.smartjob.creacionusuarios.persistence.dto.TelefonoEntity;
import com.smartjob.creacionusuarios.persistence.dto.UsuarioEntity;
import com.smartjob.creacionusuarios.utils.Md5Generator;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioSpringRepository repository;

    public UsuarioRepositoryImpl(final UsuarioSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        var usuarioEntidad = new UsuarioEntity(
                1L,
                usuario.getName(),
                usuario.getEmail(),
                Md5Generator.generateMd5(usuario.getPassword()),
                true
        );

        var telefonosEntities = usuario.getTelefonos().stream().map(tm ->
                fromModel(tm, usuarioEntidad)
        ).toList();

        telefonosEntities.forEach(usuarioEntidad::addTelefono);

        var usuarioGuardado = repository.save(usuarioEntidad);

        return toModel(usuarioGuardado);
    }

    public Long topId() {
        return repository.findTopByOrderByRestIdDesc().map(UsuarioEntity::getRestId).orElse(0L);
    }

    private Usuario toModel(UsuarioEntity usuarioGuardado) {
        var telefonos = usuarioGuardado.getTelefonos().stream().map(
                this::toModel
        ).toList();

        return new Usuario(
                usuarioGuardado.getActive(),
                usuarioGuardado.getToken(),
                usuarioGuardado.getLastLogin(),
                usuarioGuardado.getModified(),
                usuarioGuardado.getCreated(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getName(),
                usuarioGuardado.getRestId(),
                telefonos.toArray(new Telefono[0])
        );
    }

    private Telefono toModel(TelefonoEntity te) {
        return new Telefono(
                te.getNumber(),
                te.getCityCode(),
                te.getCountryCode());
    }

    public TelefonoEntity fromModel(Telefono telefono, UsuarioEntity usuario) {
        return new TelefonoEntity(
                telefono.getNumber(),
                telefono.getCityCode(),
                telefono.getCountryCode(),
                usuario
        );
    }
}
