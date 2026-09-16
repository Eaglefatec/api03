package com.eagle.fusex.solicitacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitacaoMedicaRepository extends JpaRepository<SolicitacaoMedica, Long> {
    Optional<SolicitacaoMedica> findByTokenPublico(String tokenPublico);
    Optional<SolicitacaoMedica> findByMedicoIdAndPacienteIdAndValidaTrue(Long medicoId, Long pacienteId);
}
