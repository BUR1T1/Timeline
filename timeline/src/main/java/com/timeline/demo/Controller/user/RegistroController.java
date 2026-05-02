package com.timeline.demo.Controller.user;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Repository.RegistroRepository;
import com.timeline.demo.Service.RegistroService;
import com.timeline.demo.model.Registro.Registro;
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

    @Autowired
    RegistroRepository registroRepository;

    @PostMapping
    public ResponseEntity<RegistroResponseDTO> criar( @RequestBody RegistroDto dto) {
        RegistroResponseDTO response = service.criaregistro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{registroId}")
    public ResponseEntity<Void> moverParaLixeira(@PathVariable UUID registroId, @PathVariable UUID timelineId) {
        service.moverParaLixeira(registroId,timelineId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar-registro/{registroId}")
    public ResponseEntity atualizarregistro(@PathVariable UUID registroId, @RequestBody RegistroDto dto){
        RegistroResponseDTO res = service.atualizarRegistro(registroId,dto);
        return ResponseEntity.status(HttpStatus.OK).body("registro criado" + res);
    }

    @GetMapping("/listar-inativos")
    public List<Registro> buscarinativos(){
        return service.listarregistrosDeletados();
    }

    @GetMapping("/listar-ativos")
    public List<Registro> buscarAtivos(){
        return service.listarregistrosativos();
    }

    @GetMapping("/findAll")
    public List<Registro> findAll(){
        return service.getAllRegistros();
    }

    @GetMapping("/busdcarPorId")
    public RegistroResponseDTO buscarPorId(@PathVariable UUID registroId){
        return service.buscarPorId(registroId);
    }

    @DeleteMapping("/esvaziar-lixeira/{id}/{}")
    public ResponseEntity esvaziarlixeira(@PathVariable UUID registroId, @PathVariable UUID timelineId){
        service.deletarPermanentemente(registroId, timelineId);
        return ResponseEntity.status(HttpStatus.OK).body("deletado com sucesso" + registroId);
    }











}
