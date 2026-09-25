package com.eagle.fusex.ocs;

import com.eagle.fusex.solicitacao.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OcsRepository extends JpaRepository<Ocs, Long> {

    List<Ocs> findByOcsProcedimentos_Procedimento_ProcCodigoDgp(String procCodigoDgp);

    List<Ocs> findByEspecialidadesIn(Set<Especialidade> especialidades);

    Optional<Ocs> findByOcsNome(String ocsNome);
}
