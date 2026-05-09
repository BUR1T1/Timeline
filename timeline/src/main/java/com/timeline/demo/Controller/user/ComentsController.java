package com.timeline.demo.Controller.user;
import com.timeline.demo.Dto.RegistrosDTO.comentsDTO.ComentsDto;
import com.timeline.demo.Service.ComentsService;
import com.timeline.demo.model.Registro.Coments.Coments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/me/coments")
public class ComentsController {

    @Autowired
    ComentsService comentsService;

    @PostMapping("/criar-comentario/{timelineId}")
    public ResponseEntity criarComentario(@RequestBody ComentsDto comentsDto, @PathVariable UUID timelineId){
        Coments coments = comentsService.criarComntario(comentsDto, timelineId);
        return ResponseEntity.status(HttpStatus.CREATED).body(coments);
    }

    @GetMapping("/listar-comentario/{timelineId}")
    public ResponseEntity<List<Coments>> listarCómentario(@PathVariable UUID timelineId){
        List<Coments> listRes = comentsService.listarComents(timelineId);
        return ResponseEntity.status(HttpStatus.OK).body(listRes);
    }

    @PutMapping("/inativar-comentario/{timelineId}/{comentsId}")
    public ResponseEntity inativarcomentario(@PathVariable UUID timelineId, @PathVariable UUID comentsId){
        comentsService.inativarComentario(timelineId,comentsId);
        return ResponseEntity.status(HttpStatus.OK).body("Comentario inativado com sucesso!");
    }

    @DeleteMapping("/deletar-coments/{timelineId}/{comentarioId}")
    public ResponseEntity deletarComentario(@PathVariable UUID timelineId,@PathVariable UUID comentarioId){
        comentsService.deletarPermanent(timelineId, comentarioId);
        return ResponseEntity.status(HttpStatus.OK).body("Comentario Deletado permanentmente");
    }

}
