package com.timeline.demo.Controller;

import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/public")
public class ForPublic {

    @Autowired
    RegistroService service;

    @GetMapping("usuarios/{usuarioId}/timeline")
    public ResponseEntity<List<RegistroResponseDTO>> listar(UUID usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }


    @GetMapping("/usuarios/{usuarioId}/timeline/{registroId}")
    public ResponseEntity<RegistroResponseDTO> buscarPorId(
     UUID usuarioId,
     UUID registroId
    ) {
        return ResponseEntity.ok(service.buscarPorId(usuarioId, registroId));
    }

}
