package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.comentsDTO.ComentsDto;
import com.timeline.demo.Repository.ComentsRepository;
import com.timeline.demo.Repository.TimeLineRepository;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.TimeLine;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.timeline.demo.Service.UsuarioService.getUsuarioLogado;

@Service
public class ComentsService {

    @Autowired
    ComentsRepository comentsRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TimeLineRepository timeLineRepository;

    public Coments criarComntario(ComentsDto comentsDto, UUID timeLineId){

        Usuario usuario = getUsuarioLogado();
        TimeLine timeLine = timeLineRepository.findById(timeLineId).orElseThrow(()
        -> new RuntimeException("Time line não encontrada"));

        Coments comentsDtoNew = new Coments();
        comentsDtoNew.setTimeLine(timeLine);
        comentsDtoNew.setUsuario(usuario);
        comentsDtoNew.setComentario(comentsDto.getComentario());

        return comentsRepository.save(comentsDtoNew);
    }

    public void adicionarComnetario(UUID timelineId, UUID comentsId){

        Coments coments = comentsRepository.findById(comentsId).orElseThrow(
                ()-> new RuntimeException("Comentaio não encontrado"));

        TimeLine timeLine = timeLineRepository.findById(timelineId).orElseThrow(
                () -> new RuntimeException("Time line não encontrada"));

        coments.setTimeLine(timeLine);
        comentsRepository.save(coments);
    }






}
