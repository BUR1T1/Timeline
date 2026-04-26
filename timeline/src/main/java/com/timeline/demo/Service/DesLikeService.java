package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.comentsDTO.likeDto.DesLikeDto;
import com.timeline.demo.Repository.*;
import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.Registro.Coments.DesLike;
import com.timeline.demo.model.Registro.Coments.Like;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class DesLikeService {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    RegistroRepository registroRepository;

    @Autowired
    ComentsRepository comentsRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DeslikeRepository deslikeRepository;

    @Autowired
    UsuarioService usuarioService;

    public DesLike criarDeslike(DesLikeDto desLikeDto){
        Usuario usuario = usuarioService.getUsuarioLogado();

        DesLike newdesLike = new DesLike();
        newdesLike.setUsuario(usuario);
        newdesLike.setTxt(desLikeDto.getTxt());

        return deslikeRepository.save(newdesLike);
    }

    public DesLike buscarDesLike(UUID comentarioId, UUID usuarioId){

        Coments coments = comentsRepository.findById(comentarioId).orElseThrow(() -> new RuntimeException("Comentario não encontrado"));
        List<DesLike> desLikesList = coments.getDesLike();
        for (DesLike desLike : desLikesList){
            if (desLike.getUsuario().getId().equals(usuarioId)){
                return desLike;            }
        }
        return null;
    }


    public void darDeslike(UUID comentarioId){

        Usuario usuario = usuarioService.getUsuarioLogado();
        Coments coments = comentsRepository.findById(comentarioId).orElseThrow(() -> new RuntimeException("Comentario não encontrado"));

        Optional<DesLike> desLikeList = deslikeRepository.existsByUsuarioAndComents(usuario,coments);
        for (DesLike desLikeExist : coments.getDesLike()){
            if (desLikeExist.getUsuario().getId().equals(usuario.getId()) && !desLikeExist.isDeletado()){
                return;
            }
        }

        List<Like> likes = coments.getLikes();
        for (Like likeExist : likes){
            if (likeExist.getUsuario().getId().equals(usuario.getId()) && !likeExist.isDeletado()) {
                likeExist.deletar();
                return;
            }
        }

        DesLike desLike = new DesLike();
        desLike.setUsuario(usuario);
        desLike.setComents(coments);

        deslikeRepository.save(desLike);
    }


    //criar metodo de retirar likes apagalos por inteiro





}
