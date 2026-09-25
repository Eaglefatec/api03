package com.eagle.fusex.medico.dto;

import com.eagle.fusex.medico.Medico;

public class MedicoResumoResponse {

    private String nome;
    private String crm;

    public MedicoResumoResponse() {
    }

    public MedicoResumoResponse(String nome, String crm) {
        this.nome = nome;
        this.crm = crm;
    }

    public static MedicoResumoResponse from(Medico medico) {
        if (medico == null) {
            return null;
        }
        return new MedicoResumoResponse(medico.getNome(), medico.getCrm());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
