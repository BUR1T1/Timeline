package com.timeline.demo.Service;
import com.timeline.demo.Repository.TimeLineRepository;
import com.timeline.demo.model.TimeLine;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeLineService {

    @Autowired
    TimeLineRepository timeLineRepository;


    public TimeLine criarTimeline(Usuario usuario){
        TimeLine newtimeline = new TimeLine();
        newtimeline.setUsuario(usuario);

        return timeLineRepository.save(newtimeline);
    }


    //=======================================================================
    //ROTAS PUBLICAS
    //=======================================================================

    public TimeLine chamarTimeline(Usuario usuario){

        TimeLine timeline = timeLineRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        return timeline;
    }



}
