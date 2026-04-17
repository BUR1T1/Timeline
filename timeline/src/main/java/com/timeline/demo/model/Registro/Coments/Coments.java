package com.timeline.demo.model.Registro.Coments;

import com.timeline.demo.model.EntityBase;
import com.timeline.demo.model.Registro.Registro;
import com.timeline.demo.model.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "COMENTARIOS")
public class Coments extends EntityBase {


    private String comentario ;

    private List<Like> likes;
    private List<DesLike> desLike;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "REGISTRO_id", nullable = false)
    private Registro registro;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<DesLike> getDesLike() {
        return desLike;
    }

    public void setDesLike(List<DesLike> desLike) {
        this.desLike = desLike;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
