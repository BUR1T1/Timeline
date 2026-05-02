package com.timeline.demo.Dto.UsuarioDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.timeline.demo.model.TimeLine;

import java.util.UUID;

public class UsuarioResponseDto {

    private String nome;
    private String email;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
