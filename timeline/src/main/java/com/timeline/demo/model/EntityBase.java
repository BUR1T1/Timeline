package com.timeline.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean deletado = false;

    private LocalDateTime deletadoEm;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }



    public void deletar() {
        this.deletado = true;
        this.deletadoEm = LocalDateTime.now();
    }

    public void restaurar() {
        this.deletado = false;
        this.deletadoEm = null;
    }

    /* ===== Getters ===== */

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDeletado() {
        return deletado;
    }

    public LocalDateTime getDeletadoEm() {
        return deletadoEm;
    }
}
