package com.timeline.demo.Service;
import com.timeline.demo.Repository.TimeLineRepository;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.TimeLine;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeLineService {

    @Autowired
    TimeLineRepository timeLineRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public TimeLine criarTimeline(Usuario usuario){
        TimeLine newtimeline = new TimeLine();
        newtimeline.setUsuario(usuario);
        timeLineRepository.save(newtimeline);

        return newtimeline;
    }


}
