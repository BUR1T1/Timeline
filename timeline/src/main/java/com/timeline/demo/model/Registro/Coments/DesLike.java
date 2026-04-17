package com.timeline.demo.model.Registro.Coments;

import com.timeline.demo.model.EntityBase;
import com.timeline.demo.model.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class DesLike extends EntityBase {

    @OneToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    private String txt = "Não gostei";

    @OneToOne
    @JoinColumn(name = "COMENTARIO_ID")
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
