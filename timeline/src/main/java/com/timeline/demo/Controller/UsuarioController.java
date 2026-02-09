package com.timeline.demo.Controller;

import com.timeline.demo.Dto.UsuarioDTO.UsuarioDto;
import com.timeline.demo.Dto.UsuarioDTO.UsuarioResponseDto;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@RequestBody UsuarioDto usuarioDto) {
        // Converter DTO para entidade
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());

        // Salvar no banco
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        // Converter para ResponseDto
        UsuarioResponseDto response = new UsuarioResponseDto();
        response.setNome(usuarioSalvo.getNome());
        response.setEmail(usuarioSalvo.getEmail());
        response.setSenha(usuarioSalvo.getSenha());

        return ResponseEntity.ok(response);
    }
}

