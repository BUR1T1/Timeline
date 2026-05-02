package com.timeline.demo.Controller.user;

import com.timeline.demo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("me/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

}

