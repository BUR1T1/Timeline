package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.comentsDTO.ComentsDto;
import com.timeline.demo.Repository.ComentsRepositoey;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.timeline.demo.Service.UsuarioService.getUsuarioLogado;

@Service
public class ComentsService {

    @Autowired
    ComentsRepositoey comentsRepositoey;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Coments criarComntario(ComentsDto comentsDto){

        Usuario usuario = getUsuarioLogado();

        Coments comentsDtoNew = new Coments();
        comentsDtoNew.setUsuario(usuario);
        comentsDtoNew.setComentario(comentsDto.getComentario());

        return comentsRepositoey.save(comentsDtoNew);
    }







}
