package com.eagle.fusex.ocs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, String> {

    List<Procedimento> findTop20ByProcDescricaoContainingIgnoreCaseOrderByProcDescricao(String texto);
}
