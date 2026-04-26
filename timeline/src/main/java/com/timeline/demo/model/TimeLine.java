package com.timeline.demo.model;

import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.Registro.Registro;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TimeLine extends EntityBase {

    @OneToOne
    @JoinColumn(name = "Usuario_id")
    private Usuario usuario;

    @OneToMany
    @Column(name = "REGISTRO_TIME")
    private List<Registro> registros;

    @OneToMany
    @Column(name = "Coments")
    private List<Coments> coments;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public List<Coments> getComents() {
        return coments;
    }

    public void setComents(List<Coments> coments) {
        this.coments = coments;
    }
}
