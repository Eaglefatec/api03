package com.eagle.fusex.ocs.dto;

public class OcsResponse {

    private Long ocsId;
    private String ocsNome;
    private String ocsEnderecoCidade;
    private String ocsEnderecoUf;

    public OcsResponse() {
    }

    public OcsResponse(Long ocsId, String ocsNome, String ocsEnderecoCidade, String ocsEnderecoUf) {
        this.ocsId = ocsId;
        this.ocsNome = ocsNome;
        this.ocsEnderecoCidade = ocsEnderecoCidade;
        this.ocsEnderecoUf = ocsEnderecoUf;
    }

    public Long getOcsId() {
        return ocsId;
    }

    public void setOcsId(Long ocsId) {
        this.ocsId = ocsId;
    }

    public String getOcsNome() {
        return ocsNome;
    }

    public void setOcsNome(String ocsNome) {
        this.ocsNome = ocsNome;
    }

    public String getOcsEnderecoCidade() {
        return ocsEnderecoCidade;
    }

    public void setOcsEnderecoCidade(String ocsEnderecoCidade) {
        this.ocsEnderecoCidade = ocsEnderecoCidade;
    }

    public String getOcsEnderecoUf() {
        return ocsEnderecoUf;
    }

    public void setOcsEnderecoUf(String ocsEnderecoUf) {
        this.ocsEnderecoUf = ocsEnderecoUf;
    }
}