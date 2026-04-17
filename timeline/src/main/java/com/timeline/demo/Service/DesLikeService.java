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

import static com.timeline.demo.Service.UsuarioService.getUsuarioLogado;

@Service
public class DesLikeService {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    RegistroRepository registroRepository;

    @Autowired
    ComentsRepositoey comentsRepositoey;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DeslikeRepository deslikeRepository;

    public DesLike criarDeslike(DesLike desLike){
        Usuario usuario = getUsuarioLogado();

        DesLike newdesLike = new DesLike();
        newdesLike.setUsuario(usuario);
        newdesLike.setTxt(desLike.getTxt());

        return deslikeRepository.save(newdesLike);
    }

    public DesLike buscarDesLike(UUID comentarioId, UUID usuarioId){

        Coments coments = comentsRepositoey.findById(comentarioId).orElseThrow(() -> new RuntimeException("Comentario não encontrado"));
        List<DesLike> desLikesList = coments.getDesLike();
        for (DesLike desLike : desLikesList){
            if (desLike.getUsuario().getId().equals(usuarioId)){
                return desLike;            }
        }
        return null;
    }
}
