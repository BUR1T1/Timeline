package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Repository.ComentsRepositoey;
import com.timeline.demo.Repository.RegistroRepository;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.Registro.Coments.Like;
import com.timeline.demo.model.Registro.Registro;
import com.timeline.demo.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.timeline.demo.Service.UsuarioService.getUsuarioLogado;

@Service
public class RegistroService {

    private final RegistroRepository registroRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentsRepositoey comentsRepositoey;

    public RegistroService(ComentsRepositoey comentsRepositoey,RegistroRepository registroRepository, UsuarioRepository usuarioRepository) {
        this.registroRepository = registroRepository;
        this.usuarioRepository = usuarioRepository;
        this.comentsRepositoey = comentsRepositoey;
    }



    public RegistroResponseDTO criar(RegistroDto dto) {
        Usuario usuario = UsuarioService.getUsuarioLogado();

        Registro registro = new Registro();
        registro.setTitulo(dto.getTitulo());
        registro.setDescricao(dto.getDescricao());
        registro.setDataInicio(dto.getDataInicio());
        registro.setDataFim(dto.getDataFim());
        registro.setImagemUrl(dto.getImagemUrl());
        registro.setUsuario(usuario);

        Registro salvo = registroRepository.save(registro);
        return toResponseDTO(salvo);
    }

    public List<RegistroResponseDTO> criarEmLote(List<RegistroDto> dtos) {
       Usuario usuario = UsuarioService.getUsuarioLogado();

        return dtos.stream().map(dto -> {
            Registro registro = new Registro();
            registro.setTitulo(dto.getTitulo());
            registro.setDescricao(dto.getDescricao());
            registro.setDataInicio(dto.getDataInicio());
            registro.setDataFim(dto.getDataFim());
            registro.setImagemUrl(dto.getImagemUrl());
            registro.setUsuario(usuario);
            return toResponseDTO(registroRepository.save(registro));
        }).toList();
    }

    public void moverParaLixeira(UUID registroId) {
        Usuario usuario = UsuarioService.getUsuarioLogado();

        Registro registro = registroRepository.findById(registroId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        if (!registro.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Acesso negado: O registro não pertence ao usuário logado.");
        }

        registro.deletar();
        registroRepository.save(registro);
    }











    //=======================================================================
    //ROTAS PUBLICAS
    //=======================================================================

    public List<RegistroResponseDTO> listarPorUsuario(UUID usuarioId) {
        return registroRepository.findByUsuarioIdAndDeletadoFalse(usuarioId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public RegistroResponseDTO buscarPorId(UUID usuarioId, UUID registroId) {
        Registro registro = registroRepository.findById(registroId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        if (!registro.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Acesso negado ao registro");
        }

        return toResponseDTO(registro);
    }

    private RegistroResponseDTO toResponseDTO(Registro registro) {
        RegistroResponseDTO dto = new RegistroResponseDTO();
        dto.setId(registro.getId());
        dto.setTitulo(registro.getTitulo());
        dto.setDescricao(registro.getDescricao());
        dto.setDataInicio(registro.getDataInicio());
        dto.setDataFim(registro.getDataFim());
        dto.setImagemUrl(registro.getImagemUrl());
        return dto;
    }
}