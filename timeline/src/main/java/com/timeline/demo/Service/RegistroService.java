package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Dto.TimelineDto;
import com.timeline.demo.Repository.ComentsRepository;
import com.timeline.demo.Repository.RegistroRepository;
import com.timeline.demo.Repository.TimeLineRepository;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Registro.Registro;
import com.timeline.demo.model.TimeLine;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegistroService {

    @Autowired
    private  RegistroRepository registroRepository;

    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Autowired
    private  ComentsRepository comentsRepository;

    @Autowired
    private TimeLineRepository timeLineRepository;


    public RegistroResponseDTO criaregistro(RegistroDto dto) {

        Registro registro = new Registro();
        registro.setTitulo(dto.getTitulo());
        registro.setDescricao(dto.getDescricao());
        registro.setDataInicio(dto.getDataInicio());
        registro.setDataFim(dto.getDataFim());
        registro.setImagemUrl(dto.getImagemUrl());


        UUID timelineId = dto.getTimeLine().getId();
        TimeLine timeLine = timeLineRepository.findById(timelineId).orElseThrow(() -> new RuntimeException("timeLine não encontrada"));
        registro.setTimeLine(timeLine);

        Registro salvo = registroRepository.save(registro);
        return toResponseDTO(salvo);
    }

    public List<RegistroResponseDTO> criarEmLote(List<RegistroDto> dtos) {

        return dtos.stream().map(dto -> {
            Registro registro = new Registro();
            registro.setTitulo(dto.getTitulo());
            registro.setDescricao(dto.getDescricao());
            registro.setDataInicio(dto.getDataInicio());
            registro.setDataFim(dto.getDataFim());
            registro.setImagemUrl(dto.getImagemUrl());

            UUID timelineId = dto.getTimeLine().getId();
            TimeLine timeLine = timeLineRepository.findById(timelineId).orElseThrow(() -> new RuntimeException("timeLine não encontrada"));
            registro.setTimeLine(timeLine);


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