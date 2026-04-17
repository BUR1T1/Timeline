package com.timeline.demo.Repository;

import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.Registro.Coments.Like;
import com.timeline.demo.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends BaseRepository<Like>{

    @Override
    Optional<Like> findById(UUID uuid);

    List<Like> existsByUsuarioAndComents(Usuario usuario, Coments coments);
}
