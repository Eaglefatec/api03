package com.eagle.fusex.ocs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OcsProcedimentoRepository extends JpaRepository<OcsProcedimento, OcsProcedimentoId> {

    Optional<OcsProcedimento> findByOcs_OcsIdAndProcedimento_ProcCodigoDgp(Long ocsId, String procCodigoDgp);

    List<OcsProcedimento> findByProcedimento_ProcCodigoDgp(String procCodigoDgp);
}
