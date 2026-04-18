package com.timeline.demo.Dto.RegistrosDTO.comentsDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class ComentsDto {

    private UUID id;

    private String comentario;

    private UUID usuario_id;
    private UUID timeline_id;

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

    public UUID getTimeline_id() {
        return timeline_id;
    }

    public void setTimeline_id(UUID timeline_id) {
        this.timeline_id = timeline_id;
    }
}
