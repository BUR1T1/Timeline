package com.timeline.demo.Service;

import com.timeline.demo.Dto.UsuarioDTO.UsuarioDto;
import com.timeline.demo.Dto.UsuarioDTO.UsuarioResponseDto;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Usuario;
import com.timeline.demo.util.JwtUtil;
import com.timeline.demo.util.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository userepository;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private JwtUtil jwtUtil;

    public void criarUsuario( UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(passwordConfig.passwordEncoder().encode(usuarioDto.getSenha()));

        Usuario usuarioSalvo = userepository.save(usuario);

    }



}
