package com.timeline.demo.Dto.UsuarioDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class UsuarioResponseDto {

    private UUID id;
    private String nome;
    private String email;
    private String Cpf;

    @JsonIgnore
    private String senha;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }
}
