package com.eagle.fusex.ocs;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "procedimento")
public class Procedimento {

    @Id
    @Column(name = "proc_codigo_dgp")
    private String procCodigoDgp;

    @Column(name = "proc_descricao", nullable = false, unique = true)
    private String procDescricao;

    @Column(name = "proc_quantidade", nullable = false)
    private Integer procQuantidade = 1;

    @ManyToMany(mappedBy = "procedimentos")
    private Set<Ocs> ocs = new HashSet<>();

    public Procedimento() {
    }

    public Procedimento(String procCodigoDgp, String procDescricao, Integer procQuantidade) {
        this.procCodigoDgp = procCodigoDgp;
        this.procDescricao = procDescricao;
        this.procQuantidade = procQuantidade;
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

    public Integer getProcQuantidade() {
        return procQuantidade;
    }

    public void setProcQuantidade(Integer procQuantidade) {
        this.procQuantidade = procQuantidade;
    }

    public Set<Ocs> getOcs() {
        return ocs;
    }

    public void setOcs(Set<Ocs> ocs) {
        this.ocs = ocs;
    }
}