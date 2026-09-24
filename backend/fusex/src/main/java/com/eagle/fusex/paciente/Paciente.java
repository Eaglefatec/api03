package com.eagle.fusex.paciente;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String cpfPrec;

    @Column(nullable = false, length = 10)
    private String om;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Paciente() {
    }

    public Paciente(String nome, String cpfPrec, String om) {
        this.nome = nome;
        this.cpfPrec = cpfPrec;
        this.om = om;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfPrec() {
        return cpfPrec;
    }

    public void setCpfPrec(String cpfPrec) {
        this.cpfPrec = cpfPrec;
    }

    public String getOm() {
        return om;
    }

    public void setOm(String om) {
        this.om = om;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
