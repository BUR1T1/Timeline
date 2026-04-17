package com.timeline.demo.Repository;

import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.Registro.Coments.DesLike;
import com.timeline.demo.model.Usuario;

import java.util.List;

public interface DeslikeRepository extends BaseRepository<DesLike>{
    List<DesLike> existsByUsuarioAndComents(Usuario usuario, Coments coments);
}
