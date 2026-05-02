package com.timeline.demo.Controller.Public;
import com.timeline.demo.Service.RegistroService;
import com.timeline.demo.model.Registro.Registro;
import com.timeline.demo.model.TimeLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/public")
public class ForPublic {

    @Autowired
    RegistroService service;

    @GetMapping("/teste")
    public String teste(){
        return "Serviço OK";
    }

    @GetMapping("/timeline/{userId}")
    public ResponseEntity<List<Registro>> getTimeline(@PathVariable UUID userId){
        List<Registro> timeline = service.listarRegistrosPublicos(userId);
        return ResponseEntity.status(HttpStatus.OK).body(timeline);
    }
}
