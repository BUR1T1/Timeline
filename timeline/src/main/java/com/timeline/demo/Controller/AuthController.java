package com.timeline.demo.Controller;

import com.timeline.demo.Dto.UsuarioDTO.UsuarioDto;
import com.timeline.demo.Dto.UsuarioDTO.UsuarioResponseDto;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Login fixo (Adm / 123)
    @PostMapping("/login-fixo")
    public ResponseEntity<String> loginFixo(@RequestBody UsuarioDto usuarioDto) {
        if ("Adm".equals(usuarioDto.getNome()) && "123".equals(usuarioDto.getSenha())) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    // Cadastro de usuário
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> register(@RequestBody UsuarioDto usuarioDto) {
        if (usuarioRepository.findByEmail(usuarioDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build(); // já existe usuário com esse email
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioDto.getSenha())); // senha criptografada

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        UsuarioResponseDto response = new UsuarioResponseDto();
        response.setNome(usuarioSalvo.getNome());
        response.setEmail(usuarioSalvo.getEmail());

        return ResponseEntity.ok(response);
    }

    // Login via banco
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDto usuarioDto) {
        return usuarioRepository.findByEmail(usuarioDto.getEmail())
                .map(usuario -> {
                    if (passwordEncoder.matches(usuarioDto.getSenha(), usuario.getSenha())) {
                        return ResponseEntity.ok("Login realizado com sucesso!");
                    } else {
                        return ResponseEntity.status(401).body("Senha incorreta");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Usuário não encontrado"));
    }
}
