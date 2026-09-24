package com.eagle.fusex.ocs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcsRepository extends JpaRepository<Ocs, Long> {

    List<Ocs> findByProcedimentos_ProcCodigoDgp(String procCodigoDgp);
}