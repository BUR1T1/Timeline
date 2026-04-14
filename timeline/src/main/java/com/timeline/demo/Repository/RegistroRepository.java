package com.timeline.demo.Repository;

import com.timeline.demo.model.Registro.Registro;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RegistroRepository
        extends BaseRepository<Registro> {

    List<Registro> findByUsuarioIdAndDeletadoFalse(UUID usuarioId);

    Optional<Registro> findByid(UUID uuid);
}

