package com.eagle.fusex.ocs;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OcsProcedimentoRepository extends JpaRepository<OcsProcedimento, OcsProcedimentoId> {

    Optional<OcsProcedimento> findByOcs_OcsIdAndProcedimento_ProcCodigoDgp(Long ocsId, String procCodigoDgp);

    @EntityGraph(attributePaths = {"ocs", "procedimento"})
    List<OcsProcedimento> findByProcedimento_ProcCodigoDgp(String procCodigoDgp);
}
