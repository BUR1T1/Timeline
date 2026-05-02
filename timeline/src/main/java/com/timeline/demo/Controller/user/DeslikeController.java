package com.timeline.demo.Controller.user;


import com.timeline.demo.Service.DesLikeService;
import com.timeline.demo.Service.LikeSevice;
import com.timeline.demo.model.Registro.Coments.DesLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/me/darLike")
public class DeslikeController {

    @Autowired
    DesLikeService desLikeService;

    @PostMapping("/dar-DesLike")
    public ResponseEntity darDesLike(@PathVariable UUID comentarioId){
        desLikeService.darDeslike(comentarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Deu like");
    }

    @PutMapping("/remover-DesLike")
    public ResponseEntity removerDeslike(@PathVariable UUID comentarioId){
        desLikeService.removerDeslike(comentarioId);

        return ResponseEntity.status(HttpStatus.OK).body("Des Like removido!!");
    }

    @GetMapping("/buscarLikes/{comentarioId}/{usuarioId}")
    public ResponseEntity getDeslikes(@PathVariable UUID comentarioId, @PathVariable UUID usuarioId){

        DesLike desLike = desLikeService.buscarDesLike(comentarioId, usuarioId);
        return ResponseEntity.status(HttpStatus.OK).body(desLike);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DesLike>> listardeslikes(){
        List<DesLike> listdesLikes = desLikeService.listarMyDeslikes();
        return ResponseEntity.status(HttpStatus.OK).body(listdesLikes);
    }

}
