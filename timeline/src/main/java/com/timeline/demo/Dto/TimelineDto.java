package com.timeline.demo.Dto;

import com.timeline.demo.model.Registro.Coments.Coments;
import com.timeline.demo.model.Registro.Registro;
import com.timeline.demo.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

public class TimelineDto {
    private Usuario usuario;
    private List<Registro> registros;
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
