package com.smartjob.creacionusuarios.persistence;

import com.smartjob.creacionusuarios.domain.model.Telefono;
import com.smartjob.creacionusuarios.domain.model.Usuario;
import com.smartjob.creacionusuarios.domain.repository.UsuarioRepository;
import com.smartjob.creacionusuarios.persistence.dto.TelefonoEntity;
import com.smartjob.creacionusuarios.persistence.dto.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioSpringRepository repository;

    public UsuarioRepositoryImpl(final UsuarioSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntidad = new UsuarioEntity(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getPassword());
        List<TelefonoEntity> telefonosEntities = usuario.getTelefonos().stream().map(tm -> fromModel(tm, usuarioEntidad)).toList();
        usuarioEntidad.setTelefonos(telefonosEntities);

        UsuarioEntity usuarioGuardado = repository.save(usuarioEntidad);

        return toModel(usuarioGuardado);
    }

    private Usuario toModel(UsuarioEntity usuarioGuardado) {
        List<Telefono> telefonos = usuarioGuardado.getTelefonos().stream().map(
                this::toModel
        ).toList();

        return new Usuario(
                usuarioGuardado.getActive(),
                usuarioGuardado.getToken(),
                usuarioGuardado.getLastLogin(),
                usuarioGuardado.getModified(),
                usuarioGuardado.getCreated(),
                usuarioGuardado.getPassword(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getName(),
                usuarioGuardado.getId(),
                telefonos.toArray(new Telefono[0])
        );
    }

    private Telefono toModel(TelefonoEntity te) {
        return new Telefono(
                te.getId(),
                te.getNumber(),
                te.getCityCode(),
                te.getCountryCode());
    }

    public TelefonoEntity fromModel(Telefono telefono, UsuarioEntity usuario) {
        return new TelefonoEntity(
                telefono.getId(),
                telefono.getCityCode(),
                telefono.getCountryCode(),
                telefono.getCountryCode(),
                usuario
        );
    }
}
