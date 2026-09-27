package com.eagle.fusex.ocs;

import com.eagle.fusex.solicitacao.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OcsRepository extends JpaRepository<Ocs, Long> {

    List<Ocs> findByOcsProcedimentos_Procedimento_ProcCodigoDgp(String procCodigoDgp);

    List<Ocs> findDistinctByEspecialidadesIn(Set<Especialidade> especialidades);

    Optional<Ocs> findByOcsNome(String ocsNome);
}
