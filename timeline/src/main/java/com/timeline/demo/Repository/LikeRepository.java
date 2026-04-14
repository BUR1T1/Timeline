package com.timeline.demo.Repository;

import com.timeline.demo.model.Registro.Coments.Like;

import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends BaseRepository<Like>{

    @Override
    Optional<Like> findById(UUID uuid);
}
