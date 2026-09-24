package com.eagle.fusex.encaminhamento;

import com.eagle.fusex.encaminhamento.dto.EncaminhamentoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/encaminhamentos")
public class EncaminhamentoController {

    private final EncaminhamentoService encaminhamentoService;

    public EncaminhamentoController(EncaminhamentoService encaminhamentoService) {
        this.encaminhamentoService = encaminhamentoService;
    }

    @PostMapping
    public ResponseEntity<EncaminhamentoResponse> enviarEncaminhamento(
            @RequestParam("medicoId") Long medicoId,
            @RequestParam("arquivo") MultipartFile arquivo) {

        EncaminhamentoResponse response = encaminhamentoService.enviarEncaminhamento(medicoId, arquivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
