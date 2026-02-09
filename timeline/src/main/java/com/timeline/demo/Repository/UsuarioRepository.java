package com.timeline.demo.Repository;

import com.timeline.demo.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario> {
    Optional<Usuario> findByEmail(String email);
}
