package com.timeline.demo.Controller;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Service.RegistroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/registros")
public class RegistroController {

    private final RegistroService service;

    public RegistroController(RegistroService service) {
        this.service = service;
    }

    // ===============================
    // Criar um registro
    // ===============================
    @PostMapping
    public ResponseEntity<RegistroResponseDTO> criar(
            @PathVariable Long usuarioId,
            @RequestBody RegistroDto dto
    ) {
        RegistroResponseDTO response = service.criar(usuarioId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ===============================
    // Criar registros em lote
    // ===============================
    @PostMapping("/batch")
    public ResponseEntity<List<RegistroResponseDTO>> criarEmLote(
            @PathVariable Long usuarioId,
            @RequestBody List<RegistroDto> dtos
    ) {
        List<RegistroResponseDTO> response = service.criarEmLote(usuarioId, dtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ===============================
    // Listar registros do usuário
    // ===============================
    @GetMapping
    public ResponseEntity<List<RegistroResponseDTO>> listar(
            @PathVariable Long usuarioId
    ) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    // ===============================
    // Buscar registro por ID
    // ===============================
    @GetMapping("/{registroId}")
    public ResponseEntity<RegistroResponseDTO> buscarPorId(
            @PathVariable Long usuarioId,
            @PathVariable Long registroId
    ) {
        return ResponseEntity.ok(service.buscarPorId(usuarioId, registroId));
    }

    // ===============================
    // Mover registro para lixeira
    // ===============================
    @DeleteMapping("/{registroId}")
    public ResponseEntity<Void> moverParaLixeira(
            @PathVariable Long usuarioId,
            @PathVariable Long registroId
    ) {
        service.moverParaLixeira(usuarioId, registroId);
        return ResponseEntity.noContent().build();
    }
}
