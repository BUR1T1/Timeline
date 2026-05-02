package com.timeline.demo.Service;

import com.timeline.demo.Dto.UsuarioDTO.UsuarioDto;
import com.timeline.demo.Dto.UsuarioDTO.UsuarioResponseDto;
import com.timeline.demo.Repository.TimeLineRepository;
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
    UsuarioRepository usuarioRepository;

    @Autowired
    TimeLineRepository timeLineRepository;

    @Autowired
    TimeLineService timeLineService;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private JwtUtil jwtUtil;

    public UsuarioResponseDto criarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(passwordConfig.passwordEncoder().encode(usuarioDto.getSenha()));
        usuarioRepository.save(usuario);

        TimeLine newtime = timeLineService.criarTimeline(usuario);
        usuario.setTimeLine(newtime);


        timeLineRepository.save(newtime);
        usuarioRepository.save(usuario);

        return toReesponseDto(usuario);
    }

    private UsuarioResponseDto toReesponseDto(Usuario usuario){
        UsuarioResponseDto dtoNewRes = new UsuarioResponseDto();

        usuario.setNome(dtoNewRes.getNome());
        usuario.setEmail(dtoNewRes.getEmail());

        return dtoNewRes;
    }

    public Usuario getUsuarioLogado() {
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
