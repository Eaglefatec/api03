package com.eagle.fusex.solicitacao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TriagemPreGuiaRepository extends JpaRepository<TriagemPreGuia, Long> {
    @EntityGraph(attributePaths = {"ocs"})
    Optional<TriagemPreGuia> findBySolicitacaoMedicaId(Long solicitacaoMedicaId);
}
