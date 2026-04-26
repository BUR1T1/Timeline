package com.timeline.demo.model;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "usuario")
public class Usuario extends EntityBase {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @OneToOne
    @JoinColumn(name = "TimeLineUser_id")
    private TimeLine timeLine;

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }

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
}
