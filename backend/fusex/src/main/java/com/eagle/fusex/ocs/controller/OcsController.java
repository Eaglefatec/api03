package com.eagle.fusex.ocs.controller;

import com.eagle.fusex.ocs.Ocs;
import com.eagle.fusex.ocs.OcsProcedimento;
import com.eagle.fusex.ocs.OcsProcedimentoRepository;
import com.eagle.fusex.ocs.OcsRepository;
import com.eagle.fusex.ocs.Procedimento;
import com.eagle.fusex.ocs.ProcedimentoRepository;
import com.eagle.fusex.ocs.dto.OcsResponse;
import com.eagle.fusex.ocs.dto.ProcedimentoResponse;
import com.eagle.fusex.solicitacao.Especialidade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
public class OcsController {

    private final OcsRepository ocsRepository;
    private final ProcedimentoRepository procedimentoRepository;
    private final OcsProcedimentoRepository ocsProcedimentoRepository;

    public OcsController(OcsRepository ocsRepository, ProcedimentoRepository procedimentoRepository,
                         OcsProcedimentoRepository ocsProcedimentoRepository) {
        this.ocsRepository = ocsRepository;
        this.procedimentoRepository = procedimentoRepository;
        this.ocsProcedimentoRepository = ocsProcedimentoRepository;
    }

    @GetMapping("/procedimentos")
    public ResponseEntity<List<ProcedimentoResponse>> listarProcedimentos(
            @RequestParam(required = false) String busca) {
        if (busca == null || busca.isBlank()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<ProcedimentoResponse> response = procedimentoRepository
                .findTop20ByProcDescricaoContainingIgnoreCaseOrderByProcDescricao(busca).stream()
                .map(this::toProcedimentoResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/ocs", params = "procedimentoId")
    public ResponseEntity<List<OcsResponse>> listarOcsPorProcedimento(@RequestParam String procedimentoId) {
        List<OcsResponse> response = ocsProcedimentoRepository.findByProcedimento_ProcCodigoDgp(procedimentoId).stream()
                .map(this::toOcsResponseComValor)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/ocs", params = "especialidades")
    public ResponseEntity<List<OcsResponse>> listarOcsPorEspecialidades(@RequestParam Set<Especialidade> especialidades) {
        List<OcsResponse> response = ocsRepository.findByEspecialidadesIn(especialidades).stream()
                .map(OcsResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    private ProcedimentoResponse toProcedimentoResponse(Procedimento procedimento) {
        return new ProcedimentoResponse(procedimento.getProcCodigoDgp(), procedimento.getProcDescricao());
    }

    private OcsResponse toOcsResponseComValor(OcsProcedimento ocsProcedimento) {
        Ocs ocs = ocsProcedimento.getOcs();
        OcsResponse response = OcsResponse.from(ocs);
        response.setValor(ocsProcedimento.getValor());
        response.setTabelaReferencia(ocsProcedimento.getTabelaReferencia());
        return response;
    }
}
