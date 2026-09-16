package com.eagle.fusex.solicitacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TriagemPreGuiaRepository extends JpaRepository<TriagemPreGuia, Long> {
    Optional<TriagemPreGuia> findBySolicitacaoMedicaId(Long solicitacaoMedicaId);
}
