package com.timeline.demo.Service;

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
public class LikeSevice {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    ComentsRepository comentsRepository;

    @Autowired
    UsuarioService usuarioService;

    public Like buscarLike(UUID comentarioId, UUID usuarioId){
        Usuario usuario = usuarioService.getUsuarioLogado();

        Coments coments = comentsRepository.findById(comentarioId).orElseThrow(()
        -> new RuntimeException("Comentario não encontrado"));

        Optional<Like> likes = likeRepository.existsByUsuarioAndComents(usuario, coments);
        for (Like like : coments.getLikes()) {
            if (like.getUsuario().getId().equals(usuarioId)) {
                return like;
            }
        }
        return null;
    }

    public Like criarLike(Usuario usuario, Coments coments){
        Like newLike = new Like();
        newLike.setUsuario(usuario);
        newLike.setComents(coments);
        return newLike;
    }

    public Like darLike(UUID comentarioId) {
        Usuario usuario = usuarioService.getUsuarioLogado();
        Coments coments = comentsRepository.findById(comentarioId).orElseThrow(() -> new RuntimeException("Comentário não encontrado"));

        for (Like likesexist : coments.getLikes()) {
            if (likesexist.getUsuario().getId().equals(usuario.getId()) && !likesexist.isDeletado()) {
               throw  new RuntimeException("Usuario já curtiu essa publicação");
            }
        }

        List<DesLike> desLike = coments.getDesLike();
        for (DesLike desLikeExist : desLike) {
            if (desLikeExist.getUsuario().getId().equals(usuario.getId()) && !desLikeExist.isDeletado()) {
                desLikeExist.deletar();
            }
        }

        Like like = criarLike(usuario,coments);
        return likeRepository.save(like);
    }

    public  List<Like> listarMyLikes(){
        Usuario usuario = usuarioService.getUsuarioLogado();

        return likeRepository.listarMeusLiks(usuario.getId())
                .stream()
                .filter(l -> !l.isDeletado())
                .toList();
    }

    public void removerLike(UUID comentarioId){
        Usuario usuario = usuarioService.getUsuarioLogado();
        Coments coments = comentsRepository.findById(comentarioId).orElseThrow(() -> new RuntimeException("Comentario não existe"));

        for (Like likesexist : coments.getLikes()) {
            if (likesexist.getUsuario().getId().equals(usuario.getId()) && !likesexist.isDeletado()) {
                likeRepository.delete(likesexist);
                System.out.println("deletado");
                return;
            }
        }
        throw new RuntimeException("Não existe like para remover");
    }


}
