package com.timeline.demo.Controller;

import com.timeline.demo.Dto.UsuarioDTO.UsuarioDto;
import com.timeline.demo.Dto.UsuarioDTO.UsuarioResponseDto;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.Service.UsuarioService;

import com.timeline.demo.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDto usuarioDto) {
        return usuarioRepository.findByEmail(usuarioDto.getEmail())
                .map(usuario -> {
                    if (passwordEncoder.matches(usuarioDto.getSenha(), usuario.getSenha())) {
                        String token = jwtUtil.gerarToken(usuario.getEmail());
                        return ResponseEntity.ok(token);
                    } else {
                        return ResponseEntity.status(401).body("Senha incorreta");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Usuário não encontrado"));
    }

    @PostMapping("/criarConta")
    public ResponseEntity<String> cadastrousuario( @RequestBody  UsuarioDto usuarioDto){
        usuarioService.criarUsuario(usuarioDto);

        UsuarioResponseDto userRes = new UsuarioResponseDto();
        userRes.setNome(usuarioDto.getNome());
        userRes.setEmail(usuarioDto.getEmail());

        return ResponseEntity.ok( "criado com sucesso!!" + userRes);
    }
}
