package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Repository.RegistroRepository;
import com.timeline.demo.model.Registro;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

    private final RegistroRepository repository;

    public RegistroService(RegistroRepository repository) {
        this.repository = repository;
    }

    public RegistroResponseDTO criar(RegistroDto dto) {

        Registro registro = new Registro();
        registro.setTitulo(dto.getTitulo());
        registro.setDescricao(dto.getDescricao());
        registro.setDataInicio(dto.getDataInicio());
        registro.setDataFim(dto.getDataFim());

        Registro salvo = repository.save(registro);

        return toResponseDTO(salvo);
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

    //===============================================================
    //Methodo de mover  registro criado para a lixeira
    //===============================================================

    public void moverParaLixeira(Long id) {
        Registro registro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        registro.deletar();
        repository.save(registro);
    }

}
