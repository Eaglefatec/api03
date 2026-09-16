package com.eagle.fusex.solicitacao.controller;

import com.eagle.fusex.solicitacao.dto.PreencherTriagemRequest;
import com.eagle.fusex.solicitacao.dto.SolicitacaoPublicaResponse;
import com.eagle.fusex.solicitacao.dto.TriagemResponse;
import com.eagle.fusex.solicitacao.service.TriagemPreGuiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitacoes/publico")
public class SolicitacaoPublicoController {

    private final TriagemPreGuiaService service;

    public SolicitacaoPublicoController(TriagemPreGuiaService service) {
        this.service = service;
    }

    @GetMapping("/{token}")
    public ResponseEntity<SolicitacaoPublicaResponse> buscarSolicitacao(@PathVariable String token) {
        SolicitacaoPublicaResponse response = service.buscarPorToken(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{token}/triagem")
    public ResponseEntity<TriagemResponse> preencherTriagem(@PathVariable String token,
                                                             @Valid @RequestBody PreencherTriagemRequest request) {
        TriagemResponse response = service.preencherTriagem(token, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
