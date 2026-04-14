package com.timeline.demo.Dto.RegistrosDTO.comentsDTO.likeDto;

import java.time.LocalDate;
import java.util.UUID;

public class LikeDto {


    private UUID id;

    private UUID usuario_id;
    private UUID comentario_id;

    private String txt;
    private LocalDate dataInicio;
    private LocalDate dataFim;


    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
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

    public UUID getComentario_id() {
        return comentario_id;
    }

    public void setComentario_id(UUID comentario_id) {
        this.comentario_id = comentario_id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
