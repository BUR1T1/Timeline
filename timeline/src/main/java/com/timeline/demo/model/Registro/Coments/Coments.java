package com.timeline.demo.model.Registro.Coments;

import com.timeline.demo.model.EntityBase;
import com.timeline.demo.model.Registro.Registro;
import com.timeline.demo.model.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMENTARIOS")
public class Coments extends EntityBase {


    private String comentario;

    private int likes;
    private int desLike;

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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDesLike() {
        return desLike;
    }

    public void setDesLike(int desLike) {
        this.desLike = desLike;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
