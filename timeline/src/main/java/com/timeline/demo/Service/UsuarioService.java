package com.timeline.demo.Service;

import com.timeline.demo.Dto.TimelineDto;
import com.timeline.demo.Dto.UsuarioDTO.UsuarioDto;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.TimeLine;
import com.timeline.demo.model.Usuario;
import com.timeline.demo.util.JwtUtil;
import com.timeline.demo.util.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    static UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    TimeLineService timeLineService;

    @Autowired
    private JwtUtil jwtUtil;

    public void criarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(passwordConfig.passwordEncoder().encode(usuarioDto.getSenha()));
        TimeLine newtime = timeLineService.criarTimeline(usuario);

        usuario.setTimeLine(newtime);

        usuarioRepository.save(usuario);

    }

    static Usuario getUsuarioLogado() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String emailLogado;

        if (principal instanceof UserDetails userDetails) {
            emailLogado = userDetails.getUsername();
        } else {
            emailLogado = principal.toString();
        }

        return usuarioRepository.findByEmail(emailLogado)
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado"));
    }

}
