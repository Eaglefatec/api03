package com.eagle.fusex.solicitacao.controller;

import com.eagle.fusex.solicitacao.dto.CriarSolicitacaoRequest;
import com.eagle.fusex.solicitacao.dto.SolicitacaoResponse;
import com.eagle.fusex.solicitacao.service.SolicitacaoMedicaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoMedicoController {

    private final SolicitacaoMedicaService service;

    public SolicitacaoMedicoController(SolicitacaoMedicaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SolicitacaoResponse> criarSolicitacao(@Valid @RequestBody CriarSolicitacaoRequest request) {
        SolicitacaoResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
