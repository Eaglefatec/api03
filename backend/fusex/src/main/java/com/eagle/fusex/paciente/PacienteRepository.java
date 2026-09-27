package com.eagle.fusex.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCpfPrec(String cpfPrec);
}
