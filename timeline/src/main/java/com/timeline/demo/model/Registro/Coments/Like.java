package com.timeline.demo.model.Registro.Coments;

import com.timeline.demo.model.EntityBase;
import com.timeline.demo.model.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class Like extends EntityBase {

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    private String txt = "Gostei";

    @ManyToOne
    @JoinColumn(name = "COMENTARIO_ID",nullable = false)
    private Coments coments;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Coments getComents() {
        return coments;
    }

    public void setComents(Coments coments) {
        this.coments = coments;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}