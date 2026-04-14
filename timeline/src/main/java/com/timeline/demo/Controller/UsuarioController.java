package com.timeline.demo.Controller;

import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.util.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordConfig passwordConfig;


}

