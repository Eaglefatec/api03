package com.eagle.fusex.ocs;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ocs")
public class Ocs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ocs_id")
    private Long ocsId;

    @Column(name = "ocs_nome", nullable = false)
    private String ocsNome = "OCS";

    @Column(name = "ocs_inscricao_federal", nullable = false, unique = true)
    private String ocsInscricaoFederal;

    @Column(name = "ocs_endereco", nullable = false)
    private String ocsEndereco;

    @Column(name = "ocs_endereco_numero", nullable = false)
    private String ocsEnderecoNumero;

    @Column(name = "ocs_endereco_bairro", nullable = false)
    private String ocsEnderecoBairro;

    @Column(name = "ocs_endereco_cidade", nullable = false)
    private String ocsEnderecoCidade;

    @Column(name = "ocs_endereco_uf", nullable = false)
    private String ocsEnderecoUf;

    @Column(name = "ocs_endereco_cep", nullable = false)
    private String ocsEnderecoCep;

    @Column(name = "ocs_contato_nome", nullable = false)
    private String ocsContatoNome;

    @Column(name = "ocs_contato_telefone", nullable = false)
    private String ocsContatoTelefone;

    @ManyToMany
    @JoinTable(
            name = "ocs_procedimento",
            joinColumns = @JoinColumn(name = "ocs_id"),
            inverseJoinColumns = @JoinColumn(name = "proc_codigo_dgp")
    )
    private Set<Procedimento> procedimentos = new HashSet<>();

    public Ocs() {
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

    public String getOcsInscricaoFederal() {
        return ocsInscricaoFederal;
    }

    public void setOcsInscricaoFederal(String ocsInscricaoFederal) {
        this.ocsInscricaoFederal = ocsInscricaoFederal;
    }

    public String getOcsEndereco() {
        return ocsEndereco;
    }

    public void setOcsEndereco(String ocsEndereco) {
        this.ocsEndereco = ocsEndereco;
    }

    public String getOcsEnderecoNumero() {
        return ocsEnderecoNumero;
    }

    public void setOcsEnderecoNumero(String ocsEnderecoNumero) {
        this.ocsEnderecoNumero = ocsEnderecoNumero;
    }

    public String getOcsEnderecoBairro() {
        return ocsEnderecoBairro;
    }

    public void setOcsEnderecoBairro(String ocsEnderecoBairro) {
        this.ocsEnderecoBairro = ocsEnderecoBairro;
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

    public String getOcsEnderecoCep() {
        return ocsEnderecoCep;
    }

    public void setOcsEnderecoCep(String ocsEnderecoCep) {
        this.ocsEnderecoCep = ocsEnderecoCep;
    }

    public String getOcsContatoNome() {
        return ocsContatoNome;
    }

    public void setOcsContatoNome(String ocsContatoNome) {
        this.ocsContatoNome = ocsContatoNome;
    }

    public String getOcsContatoTelefone() {
        return ocsContatoTelefone;
    }

    public void setOcsContatoTelefone(String ocsContatoTelefone) {
        this.ocsContatoTelefone = ocsContatoTelefone;
    }

    public Set<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(Set<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }
}