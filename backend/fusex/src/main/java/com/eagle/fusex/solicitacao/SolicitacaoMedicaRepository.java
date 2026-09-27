package com.eagle.fusex.solicitacao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoMedicaRepository extends JpaRepository<SolicitacaoMedica, Long> {
    @EntityGraph(attributePaths = {"medico", "paciente", "medicoResponsavel", "especialidades"})
    Optional<SolicitacaoMedica> findByTokenPublico(String tokenPublico);

    List<SolicitacaoMedica> findByMedicoIdAndPacienteIdAndValidaTrue(Long medicoId, Long pacienteId);
}

