package com.timeline.demo.Controller;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Service.RegistroService;
import com.timeline.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/me/registros")
public class RegistroController {

    @Autowired
    RegistroService service;

    public RegistroController(RegistroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RegistroResponseDTO> criar( @RequestBody RegistroDto dto) {

        RegistroResponseDTO response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<RegistroResponseDTO>> criarEmLote(@RequestBody List<RegistroDto> dtos) {
        List<RegistroResponseDTO> response = service.criarEmLote(dtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{registroId}")
    public ResponseEntity<Void> moverParaLixeira(@PathVariable UUID registroId) {
        service.moverParaLixeira(registroId);
        return ResponseEntity.noContent().build();
    }
}
