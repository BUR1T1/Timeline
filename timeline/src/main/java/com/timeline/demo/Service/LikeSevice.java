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
import java.util.UUID;

import static com.timeline.demo.Service.UsuarioService.getUsuarioLogado;

@Service
public class LikeSevice {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    RegistroRepository registroRepository;

    @Autowired
    ComentsRepositoey comentsRepositoey;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Like criarLike(LikeDto likeDto){
        Usuario usuario = getUsuarioLogado();
        Like newLike = new Like();
        newLike.setUsuario(usuario);
        newLike.setTxt(likeDto.getTxt());
        return likeRepository.save(newLike);
    }

    public Like buscarLike(UUID comentarioId, UUID usuarioId){
        Usuario usuario = getUsuarioLogado();

        Coments coments = comentsRepositoey.findById(comentarioId).orElseThrow(()
        -> new RuntimeException("Comentario não encontrado"));

        List<Like> likes = likeRepository.existsByUsuarioAndComents(usuario, coments);
        for (Like like : likes) {
            if (like.getUsuario().getId().equals(usuarioId)) {
                return like;
            }
        }
        return null;
    }

    public void darLike(UUID comentarioId) {
        Usuario usuario = getUsuarioLogado();
        Coments coments = comentsRepositoey.findById(comentarioId).orElseThrow(() -> new RuntimeException("Comentário não encontrado"));

        List<Like> likes = likeRepository.existsByUsuarioAndComents(usuario, coments);
        for (Like likesexist : likes) {
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

        Like like = new Like();
        like.setUsuario(usuario);
        like.setComents(coments);

        likeRepository.save(like);
    }
}
