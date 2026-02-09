package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Repository.RegistroRepository;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Registro;
import com.timeline.demo.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistroService {

    private final RegistroRepository registroRepository;
    private final UsuarioRepository usuarioRepository;

    public RegistroService(
            RegistroRepository registroRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.registroRepository = registroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // ===============================
    // Criar registro
    // ===============================
    public RegistroResponseDTO criar(Long usuarioId, RegistroDto dto) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

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

    // ===============================
    // Criar registros em lote
    // ===============================
    public List<RegistroResponseDTO> criarEmLote(Long usuarioId, List<RegistroDto> dtos) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<RegistroResponseDTO> resposta = new ArrayList<>();

        for (RegistroDto dto : dtos) {
            Registro registro = new Registro();
            registro.setTitulo(dto.getTitulo());
            registro.setDescricao(dto.getDescricao());
            registro.setDataInicio(dto.getDataInicio());
            registro.setDataFim(dto.getDataFim());
            registro.setImagemUrl(dto.getImagemUrl());
            registro.setUsuario(usuario);

            Registro salvo = registroRepository.save(registro);
            resposta.add(toResponseDTO(salvo));
        }

        return resposta;
    }


    // ===============================
    // Listar registros por usuário
    // ===============================
    public List<RegistroResponseDTO> listarPorUsuario(Long usuarioId) {
        return registroRepository.findByUsuarioIdAndDeletadoFalse(usuarioId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // ===============================
    // Buscar registro por ID (seguro)
    // ===============================
    public RegistroResponseDTO buscarPorId(Long usuarioId, Long registroId) {

        Registro registro = registroRepository.findById(registroId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        if (!registro.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Acesso negado ao registro");
        }

        return toResponseDTO(registro);
    }

    // ===============================
    // Mover para lixeira
    // ===============================
    public void moverParaLixeira(Long usuarioId, Long registroId) {

        Registro registro = registroRepository.findById(registroId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        if (!registro.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Acesso negado ao registro");
        }

        registro.deletar();
        registroRepository.save(registro);
    }

    // ===============================
    // Mapper
    // ===============================
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
