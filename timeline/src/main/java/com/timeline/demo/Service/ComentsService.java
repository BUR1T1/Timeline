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

import java.util.List;
import java.util.UUID;

@Service
public class ComentsService {

    @Autowired
    ComentsRepository comentsRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TimeLineRepository timeLineRepository;

    @Autowired
    UsuarioService usuarioService;

    public Coments criarComntario(ComentsDto comentsDto, UUID timeLineId){

        Usuario usuario = usuarioService.getUsuarioLogado();
        TimeLine timeLine = timeLineRepository.findById(timeLineId).orElseThrow(()
        -> new RuntimeException("Time line não encontrada"));

        Coments comentsDtoNew = new Coments();
        comentsDtoNew.setTimeLine(timeLine);
        comentsDtoNew.setUsuario(usuario);
        comentsDtoNew.setComentario(comentsDto.getComentario());

        return comentsRepository.save(comentsDtoNew);
    }

    // Falta listar comentários por timeline.
    public List<Coments> listarComents(UUID timlineId){

        TimeLine timeLine = timeLineRepository.findById(timlineId).orElseThrow(() -> new RuntimeException("timeline não encontrada"));

        if (timeLine.getComents() == null){
            return List.of();
        }

        return timeLine.getComents()
                .stream()
                .filter(objcoments -> !objcoments.isDeletado())
                .toList();
    }

    //Falta deletar comentário, se isso entrar na v1.
    public Coments inativarComentario(UUID timeLineId, UUID comentsId){
        Usuario usuario = usuarioService.getUsuarioLogado();
        TimeLine timeLine = timeLineRepository.findById(timeLineId).orElseThrow(() -> new RuntimeException("timeline não encontrada"));
        Coments coments = comentsRepository.findById(comentsId).orElseThrow(() -> new RuntimeException("Comentario não encontrado "));

        usuarioDono(usuario,timeLine);

        coments.isDeletado();

        return comentsRepository.save(coments);

    }

    public void deletarPermanent(UUID timeLineId, UUID comentsId){
        Usuario usuario = usuarioService.getUsuarioLogado();
        TimeLine timeLine = timeLineRepository.findById(timeLineId).orElseThrow(() -> new RuntimeException("timeline não encontrada"));
        Coments coments = comentsRepository.findById(comentsId).orElseThrow(() -> new RuntimeException("Comentario não encontrado "));

        usuarioDono(usuario,timeLine);
        comentsRepository.delete(coments);

    }

    public void usuarioDono(Usuario user, TimeLine line){
        if (!line.getUsuario().getId().equals(user.getId())) {
            throw new RuntimeException("Acesso negado");
        }
    }




}
