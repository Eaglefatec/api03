package com.eagle.fusex.ocs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, String> {

    List<Procedimento> findTop20ByProcDescricaoContainingIgnoreCaseOrderByProcDescricao(String texto);
}
