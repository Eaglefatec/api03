package com.eagle.fusex.solicitacao.dto;

import com.eagle.fusex.solicitacao.Especialidade;

import java.util.Set;

public class SolicitacaoPublicaResponse {

    private String nomePaciente;
    private String om;
    private Set<Especialidade> especialidades;
    private String observacao;
    private Boolean triagemPreenchida;

    public SolicitacaoPublicaResponse() {
    }

    public SolicitacaoPublicaResponse(String nomePaciente, String om, Set<Especialidade> especialidades,
                                      String observacao, Boolean triagemPreenchida) {
        this.nomePaciente = nomePaciente;
        this.om = om;
        this.especialidades = especialidades;
        this.observacao = observacao;
        this.triagemPreenchida = triagemPreenchida;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getOm() {
        return om;
    }

    public void setOm(String om) {
        this.om = om;
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

    public Boolean getTriagemPreenchida() {
        return triagemPreenchida;
    }

    public void setTriagemPreenchida(Boolean triagemPreenchida) {
        this.triagemPreenchida = triagemPreenchida;
    }
}
