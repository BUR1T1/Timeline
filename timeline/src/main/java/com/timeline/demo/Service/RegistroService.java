package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Repository.RegistroRepository;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Registro;
import com.timeline.demo.model.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegistroService {

    private final RegistroRepository registroRepository;
    private final UsuarioRepository usuarioRepository;

    public RegistroService(RegistroRepository registroRepository, UsuarioRepository usuarioRepository) {
        this.registroRepository = registroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private void validarDonoDoRecurso(UUID usuarioIdDaUrl) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailLogado;

        if (principal instanceof UserDetails userDetails) {
            emailLogado = userDetails.getUsername();
        } else {
            emailLogado = principal.toString();
        }

        Usuario usuarioDaUrl = usuarioRepository.findById(usuarioIdDaUrl)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuarioDaUrl.getEmail().equals(emailLogado)) {
            throw new RuntimeException("Acesso negado: Você não tem permissão para alterar dados de outro usuário.");
        }
    }

    public RegistroResponseDTO criar(UUID usuarioId, RegistroDto dto) {
        validarDonoDoRecurso(usuarioId);

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

    public List<RegistroResponseDTO> criarEmLote(UUID usuarioId, List<RegistroDto> dtos) {
        validarDonoDoRecurso(usuarioId);

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

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

    public void moverParaLixeira(UUID usuarioId, UUID registroId) {
        validarDonoDoRecurso(usuarioId);

        Registro registro = registroRepository.findById(registroId)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        if (!registro.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Acesso negado: O registro não pertence ao usuário informado.");
        }

        registro.deletar();
        registroRepository.save(registro);
    }

    private RegistroResponseDTO toResponseDTO(Registro registro) {
        RegistroResponseDTO dto = new RegistroResponseDTO();
        dto.setId(registro.getId()); // Adicionado ID no retorno para facilitar edição no Front
        dto.setTitulo(registro.getTitulo());
        dto.setDescricao(registro.getDescricao());
        dto.setDataInicio(registro.getDataInicio());
        dto.setDataFim(registro.getDataFim());
        dto.setImagemUrl(registro.getImagemUrl());
        return dto;
    }
}