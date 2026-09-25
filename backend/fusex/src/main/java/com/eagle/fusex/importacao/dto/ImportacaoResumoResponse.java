package com.eagle.fusex.importacao.dto;

import java.util.List;

public class ImportacaoResumoResponse {

    private String mensagem;
    private List<String> avisos;

    public ImportacaoResumoResponse(String mensagem, List<String> avisos) {
        this.mensagem = mensagem;
        this.avisos = avisos;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<String> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<String> avisos) {
        this.avisos = avisos;
    }
}
