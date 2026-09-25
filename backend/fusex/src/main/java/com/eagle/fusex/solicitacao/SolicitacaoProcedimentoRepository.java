package com.eagle.fusex.solicitacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoProcedimentoRepository extends JpaRepository<SolicitacaoProcedimento, Long> {

    List<SolicitacaoProcedimento> findBySolicitacaoMedicaId(Long solicitacaoMedicaId);
}
