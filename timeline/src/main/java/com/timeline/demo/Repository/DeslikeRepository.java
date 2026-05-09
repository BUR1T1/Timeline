package com.timeline.demo.Repository;

import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.Registro.Coments.DesLike;
import com.timeline.demo.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeslikeRepository extends BaseRepository<DesLike>{
    Optional<DesLike> findByUsuarioAndComents(Usuario usuario, Coments coments);

    List<DesLike> findByUsuario_Id(UUID usuarioId);
}