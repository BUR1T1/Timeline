package com.timeline.demo.Controller;
import com.timeline.demo.Service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class ForPublic {

    @Autowired
    RegistroService service;


}
