package com.eagle.fusex.solicitacao.dto;

import com.eagle.fusex.solicitacao.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.Set;

public class CriarSolicitacaoRequest {

    @NotNull(message = "medicoId é obrigatório")
    @Positive(message = "medicoId deve ser positivo")
    private Long medicoId;

    @NotBlank(message = "nomePaciente é obrigatório")
    private String nomePaciente;

    @NotBlank(message = "om é obrigatório")
    private String om;

    @NotBlank(message = "cpfPrec é obrigatório")
    private String cpfPrec;

    @NotEmpty(message = "especialidades não pode estar vazio")
    private Set<Especialidade> especialidades;

    private String observacao;

    @Valid
    @NotEmpty(message = "procedimentos não pode estar vazio")
    private List<ProcedimentoQuantidade> procedimentos;

    public CriarSolicitacaoRequest() {
    }

    public static class ProcedimentoQuantidade {

        @NotBlank(message = "procedimentoCodigoDgp é obrigatório")
        private String procedimentoCodigoDgp;

        @NotNull(message = "quantidade é obrigatória")
        @Positive(message = "quantidade deve ser positiva")
        private Integer quantidade;

        public ProcedimentoQuantidade() {
        }

        public String getProcedimentoCodigoDgp() {
            return procedimentoCodigoDgp;
        }

        public void setProcedimentoCodigoDgp(String procedimentoCodigoDgp) {
            this.procedimentoCodigoDgp = procedimentoCodigoDgp;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
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

    public String getCpfPrec() {
        return cpfPrec;
    }

    public void setCpfPrec(String cpfPrec) {
        this.cpfPrec = cpfPrec;
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

    public List<ProcedimentoQuantidade> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<ProcedimentoQuantidade> procedimentos) {
        this.procedimentos = procedimentos;
    }
}
