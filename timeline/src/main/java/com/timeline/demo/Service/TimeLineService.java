package com.timeline.demo.Service;
import com.timeline.demo.Repository.TimeLineRepository;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.TimeLine;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TimeLineService {

    @Autowired
    TimeLineRepository timeLineRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;


    public TimeLine criarTimeline(Usuario usuario){
        TimeLine newtimeline = new TimeLine();
        newtimeline.setUsuario(usuario);

        return timeLineRepository.save(newtimeline);
    }

    public TimeLine chamarMinhaTimeline() {
        Usuario usuario = usuarioService.getUsuarioLogado();

        return timeLineRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Timeline não encontrada"));
    }

    //=======================================================================
    //ROTAS PUBLICAS
    //=======================================================================

    public TimeLine buscarTimelinePublica(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return timeLineRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Timeline não encontrada"));
    }

}
