package com.eagle.fusex.encaminhamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncaminhamentoRepository extends JpaRepository<Encaminhamento, Long> {
}
