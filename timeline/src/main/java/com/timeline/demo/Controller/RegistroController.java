package com.timeline.demo.Controller;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Service.RegistroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios/{usuarioId}/registros")
public class RegistroController {

    private final RegistroService service;

    public RegistroController(RegistroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RegistroResponseDTO> criar(
            @PathVariable UUID usuarioId,
            @RequestBody RegistroDto dto
    ) {
        RegistroResponseDTO response = service.criar(usuarioId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<RegistroResponseDTO>> criarEmLote(
            @PathVariable UUID usuarioId,
            @RequestBody List<RegistroDto> dtos
    ) {
        List<RegistroResponseDTO> response = service.criarEmLote(usuarioId, dtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RegistroResponseDTO>> listar(
            @PathVariable UUID usuarioId
    ) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }


    @GetMapping("/{registroId}")
    public ResponseEntity<RegistroResponseDTO> buscarPorId(
            @PathVariable UUID usuarioId,
            @PathVariable UUID registroId
    ) {
        return ResponseEntity.ok(service.buscarPorId(usuarioId, registroId));
    }

    @DeleteMapping("/{registroId}")
    public ResponseEntity<Void> moverParaLixeira(
            @PathVariable UUID usuarioId,
            @PathVariable UUID registroId
    ) {
        service.moverParaLixeira(usuarioId, registroId);
        return ResponseEntity.noContent().build();
    }
}
