package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Repository.RegistroRepository;
import com.timeline.demo.model.Registro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroService {

    private final RegistroRepository repository;

    public RegistroService(RegistroRepository repository) {
        this.repository = repository;
    }

    // =================================================
    // CRIAR REGISTRO
    // =================================================
    public RegistroResponseDTO criar(RegistroDto dto) {

        Registro registro = new Registro();
        registro.setTitulo(dto.getTitulo());
        registro.setDescricao(dto.getDescricao());
        registro.setDataInicio(dto.getDataInicio());
        registro.setDataFim(dto.getDataFim());
        registro.setImagemUrl(dto.getImagemUrl());

        Registro salvo = repository.save(registro);
        return toResponseDTO(salvo);
    }

    // =================================================
    // LISTAR TODOS OS REGISTROS
    // =================================================
    public List<RegistroResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // =================================================
    // BUSCAR REGISTRO POR ID
    // =================================================
    public RegistroResponseDTO buscarPorId(Long id) {
        Registro registro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        return toResponseDTO(registro);
    }

    // =================================================
    // ATUALIZAR REGISTRO
    // =================================================
    public RegistroResponseDTO atualizar(Long id, RegistroDto dto) {
        Registro registro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        registro.setTitulo(dto.getTitulo());
        registro.setDescricao(dto.getDescricao());
        registro.setDataInicio(dto.getDataInicio());
        registro.setDataFim(dto.getDataFim());
        registro.setImagemUrl(dto.getImagemUrl());

        Registro atualizado = repository.save(registro);
        return toResponseDTO(atualizado);
    }

    // =================================================
    // MOVER PARA LIXEIRA (SOFT DELETE)
    // =================================================
    public void moverParaLixeira(Long id) {
        Registro registro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        registro.deletar(); // método do EntityBase
        repository.save(registro);
    }

    // =================================================
    // CONVERSÃO PARA RESPONSE DTO
    // =================================================
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
