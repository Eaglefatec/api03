package com.eagle.fusex.ocs.controller;

import com.eagle.fusex.ocs.Ocs;
import com.eagle.fusex.ocs.OcsRepository;
import com.eagle.fusex.ocs.Procedimento;
import com.eagle.fusex.ocs.ProcedimentoRepository;
import com.eagle.fusex.ocs.dto.OcsResponse;
import com.eagle.fusex.ocs.dto.ProcedimentoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OcsController {

    private final OcsRepository ocsRepository;
    private final ProcedimentoRepository procedimentoRepository;

    public OcsController(OcsRepository ocsRepository, ProcedimentoRepository procedimentoRepository) {
        this.ocsRepository = ocsRepository;
        this.procedimentoRepository = procedimentoRepository;
    }

    @GetMapping("/procedimentos")
    public ResponseEntity<List<ProcedimentoResponse>> listarProcedimentos() {
        List<ProcedimentoResponse> response = procedimentoRepository.findAll().stream()
                .map(this::toProcedimentoResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/ocs")
    public ResponseEntity<List<OcsResponse>> listarOcsPorProcedimento(@RequestParam String procedimentoId) {
        List<OcsResponse> response = ocsRepository.findByProcedimentos_ProcCodigoDgp(procedimentoId).stream()
                .map(this::toOcsResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    private ProcedimentoResponse toProcedimentoResponse(Procedimento procedimento) {
        return new ProcedimentoResponse(procedimento.getProcCodigoDgp(), procedimento.getProcDescricao());
    }

    private OcsResponse toOcsResponse(Ocs ocs) {
        return new OcsResponse(ocs.getOcsId(), ocs.getOcsNome(), ocs.getOcsEnderecoCidade(), ocs.getOcsEnderecoUf());
    }
}