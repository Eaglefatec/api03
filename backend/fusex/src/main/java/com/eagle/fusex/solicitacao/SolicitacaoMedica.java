package com.eagle.fusex.solicitacao;

import com.eagle.fusex.medico.Medico;
import com.eagle.fusex.paciente.Paciente;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "solicitacao_medica")
public class SolicitacaoMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_responsavel_id", nullable = true)
    private Medico medicoResponsavel;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "solicitacao_especialidade", joinColumns = @JoinColumn(name = "solicitacao_id"))
    @Column(name = "especialidade")
    @Enumerated(EnumType.STRING)
    private Set<Especialidade> especialidades = new HashSet<>();

    @Column(length = 500)
    private String observacao;

    @Column(nullable = false, unique = true)
    private String tokenPublico;

    @Column(nullable = false)
    private Boolean valida = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    public SolicitacaoMedica() {
    }

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public void setMedicoResponsavel(Medico medicoResponsavel) {
        this.medicoResponsavel = medicoResponsavel;
    }

    public Set<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTokenPublico() {
        return tokenPublico;
    }

    public void setTokenPublico(String tokenPublico) {
        this.tokenPublico = tokenPublico;
    }

    public Boolean getValida() {
        return valida;
    }

    public void setValida(Boolean valida) {
        this.valida = valida;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
