package com.timeline.demo.Repository;

import com.timeline.demo.model.Registro;

import java.util.List;

public interface RegistroRepository
        extends BaseRepository<Registro> {

    List<Registro> findByUsuarioIdAndDeletadoFalse(Long usuarioId);

}

