package com.timeline.demo.Dto.RegistrosDTO.comentsDTO;

import com.timeline.demo.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

public class ComentsDto {

    private UUID id;

    private String comentario;

    private UUID usuario_id;
    private UUID registro_id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(UUID usuario_id) {
        this.usuario_id = usuario_id;
    }

    public UUID getRegistro_id() {
        return registro_id;
    }

    public void setRegistro_id(UUID registro_id) {
        this.registro_id = registro_id;
    }
}
