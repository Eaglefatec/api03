package com.eagle.fusex.solicitacao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoProcedimentoRepository extends JpaRepository<SolicitacaoProcedimento, Long> {

    @EntityGraph(attributePaths = {"procedimento"})
    List<SolicitacaoProcedimento> findBySolicitacaoMedicaId(Long solicitacaoMedicaId);
}
