package com.timeline.demo.Controller;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Service.RegistroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
@CrossOrigin(origins = "*") // libera acesso do front (Next.js)
public class RegistroController {

    private final RegistroService service;

    public RegistroController(RegistroService service) {
        this.service = service;
    }

    // =================================================
    // CRIAR REGISTRO
    // POST /registros
    // =================================================
    @PostMapping
    public ResponseEntity<RegistroResponseDTO> criar(
            @RequestBody RegistroDto dto
    ) {
        RegistroResponseDTO response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // =================================================
    // LISTAR TODOS OS REGISTROS
    // GET /registros
    // =================================================
    @GetMapping
    public ResponseEntity<List<RegistroResponseDTO>> listarTodos() {
        List<RegistroResponseDTO> registros = service.listarTodos();
        return ResponseEntity.ok(registros);
    }

    // =================================================
    // BUSCAR REGISTRO POR ID
    // GET /registros/{id}
    // =================================================
    @GetMapping("/{id}")
    public ResponseEntity<RegistroResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {
        RegistroResponseDTO registro = service.buscarPorId(id);
        return ResponseEntity.ok(registro);
    }

    // =================================================
    // ATUALIZAR REGISTRO
    // PUT /registros/{id}
    // =================================================
    @PutMapping("/{id}")
    public ResponseEntity<RegistroResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody RegistroDto dto
    ) {
        RegistroResponseDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    // =================================================
    // MOVER PARA LIXEIRA (SOFT DELETE)
    // DELETE /registros/{id}
    // =================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> moverParaLixeira(
            @PathVariable Long id
    ) {
        service.moverParaLixeira(id);
        return ResponseEntity.noContent().build();
    }
}
