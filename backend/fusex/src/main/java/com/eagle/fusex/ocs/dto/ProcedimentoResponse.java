package com.eagle.fusex.ocs.dto;

public class ProcedimentoResponse {

    private String procCodigoDgp;
    private String procDescricao;

    public ProcedimentoResponse() {
    }

    public ProcedimentoResponse(String procCodigoDgp, String procDescricao) {
        this.procCodigoDgp = procCodigoDgp;
        this.procDescricao = procDescricao;
    }

    public String getProcCodigoDgp() {
        return procCodigoDgp;
    }

    public void setProcCodigoDgp(String procCodigoDgp) {
        this.procCodigoDgp = procCodigoDgp;
    }

    public String getProcDescricao() {
        return procDescricao;
    }

    public void setProcDescricao(String procDescricao) {
        this.procDescricao = procDescricao;
    }
}