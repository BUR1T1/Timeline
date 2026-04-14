package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.comentsDTO.likeDto.DesLikeDto;
import com.timeline.demo.Repository.DeslikeRepository;
import com.timeline.demo.Repository.LikeRepository;
import com.timeline.demo.Repository.RegistroRepository;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Registro.Coments.Like;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.timeline.demo.Service.UsuarioService.getUsuarioLogado;

@Service
public class LikeSevice {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    RegistroRepository registroRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Like criarLike(DesLikeDto desLikeDto){

        Usuario usuario = getUsuarioLogado();
        Like newLike = new Like();
        newLike.setUsuario(usuario);
        newLike.setComents(desLikeDto.getComentario_id());
        newLike.setTxt(desLikeDto.getTxt());

        return likeRepository.save(newLike);
    }


}
