package com.timeline.demo.Service;

import com.timeline.demo.Repository.*;
import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.Registro.Coments.DesLike;
import com.timeline.demo.model.Registro.Coments.Like;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class DesLikeService {

    @Autowired
    ComentsRepository comentsRepository;

    @Autowired
    DeslikeRepository deslikeRepository;

    @Autowired
    TimeLineRepository timeLineRepository;

    @Autowired
    UsuarioService usuarioService;

    public DesLike criarDeslike(Usuario usuario, Coments coments){
        DesLike newdesLike = new DesLike();
        newdesLike.setUsuario(usuario);
        newdesLike.setComents(coments);
        return newdesLike;
    }

    public void darDeslike(UUID comentarioId){

        Usuario usuario = usuarioService.getUsuarioLogado();
        Coments coments = comentsRepository.findById(comentarioId).orElseThrow(
                () -> new RuntimeException("Comentario não encontrado"));

        for (DesLike desLikeExist : coments.getDesLike()){
            if (desLikeExist.getUsuario().getId().equals(usuario.getId()) && !desLikeExist.isDeletado()){
                return;
            }
        }

        List<Like> likes = coments.getLikes();
        for (Like likeExist : likes){
            if (likeExist.getUsuario().getId().equals(usuario.getId()) && !likeExist.isDeletado()) {
                likeExist.deletar();

            }
        }
        DesLike desLike = criarDeslike(usuario,coments);
        deslikeRepository.save(desLike);
    }

    public DesLike buscarDesLike(UUID comentarioId, UUID usuarioId){
        Coments coments = comentsRepository.findById(comentarioId).orElseThrow(() -> new RuntimeException("Comentario não encontrado"));
        List<DesLike> desLikesList = coments.getDesLike();
        for (DesLike desLike : desLikesList){
            if (desLike.getUsuario().getId().equals(usuarioId)){
                return desLike;
            }
        }
         throw  new RuntimeException("Deslikes não encontrados");
    }

    public List<DesLike> listarMyDeslikes(){
        Usuario usuario = usuarioService.getUsuarioLogado();

        return deslikeRepository.findBydeslikeUser(usuario.getId())
                .stream()
                .filter(d -> !d.isDeletado())
                .toList();
    }

    public void removerDeslike(UUID comentarioId){
        Usuario usuario = usuarioService.getUsuarioLogado();
        Coments coments = comentsRepository.findById(comentarioId).orElseThrow(()-> new RuntimeException("Erro ao buscarDeslike"));

        for (DesLike desLikeExist : coments.getDesLike()){
            if (desLikeExist.getUsuario().getId().equals(usuario.getId()) && !desLikeExist.isDeletado()){
                deslikeRepository.delete(desLikeExist);
                return;
            }
        }
        throw new RuntimeException("Não existe deslike");
    }




}
