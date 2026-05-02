package com.timeline.demo.Controller.user;

import com.timeline.demo.Service.LikeSevice;
import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.Registro.Coments.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("me/like")
public class LikeController {

    @Autowired
    LikeSevice likeSevice;

    @PostMapping("/darlike")
    public ResponseEntity darlikes(@PathVariable UUID comentarioId){
        Like like = likeSevice.darLike(comentarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(like);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Like>> listarMLikes(){
        List<Like> listLikes = likeSevice.listarMyLikes();
        return ResponseEntity.status(HttpStatus.OK).body(listLikes);
    }

    @GetMapping("/buscar-likes")
    public ResponseEntity buscarlike(@PathVariable UUID comentarioId, @PathVariable UUID usuarioId){
        Like like = likeSevice.buscarLike(comentarioId, usuarioId);
        return ResponseEntity.status(HttpStatus.OK).body(like);
    }

    @GetMapping
    @DeleteMapping("/deletar")
    public void deletarLike(@PathVariable UUID comentarioId){
         likeSevice.removerLike(comentarioId);
    }






}
