package com.timeline.demo.Repository;

import com.timeline.demo.model.TimeLine;
import com.timeline.demo.model.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface TimeLineRepository extends BaseRepository<TimeLine> {

    default Optional<TimeLine> findById(UUID uuid) {
        return Optional.empty();
    }

    default Optional<TimeLine> findByUsuario(Usuario usuario){
        return Optional.empty();
    }





}
