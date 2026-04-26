package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.comentsDTO.likeDto.LikeDto;
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

    private Like criarLike(LikeDto likeDto){
        Usuario usuario = usuarioService.getUsuarioLogado();
        Like newLike = new Like();
        newLike.setUsuario(usuario);
        newLike.setTxt(likeDto.getTxt());
        return likeRepository.save(newLike);
    }


    public void darLike(UUID comentarioId) {
        Usuario usuario = usuarioService.getUsuarioLogado();
        Coments coments = comentsRepository.findById(comentarioId).orElseThrow(() -> new RuntimeException("Comentário não encontrado"));

        Optional<Like> likes = likeRepository.existsByUsuarioAndComents(usuario, coments);
        for (Like likesexist : coments.getLikes()) {
            if (likesexist.getUsuario().getId().equals(usuario.getId()) && !likesexist.isDeletado()) {
                System.out.println("Usuario já curtiu essa publicação");
                return;
            }
        }

        List<DesLike> desLike = coments.getDesLike();
        for (DesLike desLikeExist : desLike) {
            if (desLikeExist.getUsuario().getId().equals(usuario.getId()) && !desLikeExist.isDeletado()) {
                desLikeExist.deletar();
            }
        }

        Like like = criarLike()

        likeRepository.save(like);
    }


}
