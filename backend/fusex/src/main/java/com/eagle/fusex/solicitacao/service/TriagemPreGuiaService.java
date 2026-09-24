package com.eagle.fusex.solicitacao.service;

import com.eagle.fusex.shared.exception.SolicitacaoNaoEncontradaException;
import com.eagle.fusex.shared.exception.TriagemJaPreenchidaException;
import com.eagle.fusex.solicitacao.SolicitacaoMedica;
import com.eagle.fusex.solicitacao.SolicitacaoMedicaRepository;
import com.eagle.fusex.solicitacao.TriagemPreGuia;
import com.eagle.fusex.solicitacao.TriagemPreGuiaRepository;
import com.eagle.fusex.solicitacao.dto.PreencherTriagemRequest;
import com.eagle.fusex.solicitacao.dto.SolicitacaoPublicaResponse;
import com.eagle.fusex.solicitacao.dto.TriagemResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class TriagemPreGuiaService {

    private final SolicitacaoMedicaRepository solicitacaoRepository;
    private final TriagemPreGuiaRepository triagemRepository;

    public TriagemPreGuiaService(SolicitacaoMedicaRepository solicitacaoRepository,
                                 TriagemPreGuiaRepository triagemRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.triagemRepository = triagemRepository;
    }

    public SolicitacaoPublicaResponse buscarPorToken(String token) {
        SolicitacaoMedica solicitacao = validarSolicitacao(token);

        boolean triagemPreenchida = triagemRepository.findBySolicitacaoMedicaId(solicitacao.getId())
                .isPresent();

        return new SolicitacaoPublicaResponse(
                solicitacao.getPaciente().getNome(),
                solicitacao.getPaciente().getOm(),
                solicitacao.getEspecialidades(),
                solicitacao.getObservacao(),
                triagemPreenchida
        );
    }

    @Transactional
    public TriagemResponse preencherTriagem(String token, PreencherTriagemRequest request) {
        SolicitacaoMedica solicitacao = validarSolicitacao(token);

        if (triagemRepository.findBySolicitacaoMedicaId(solicitacao.getId()).isPresent()) {
            throw new TriagemJaPreenchidaException("Triagem já foi preenchida para esta solicitação");
        }

        TriagemPreGuia triagem = new TriagemPreGuia();
        triagem.setSolicitacaoMedica(solicitacao);
        triagem.setCpfPrec(request.getCpfPrec());
        triagem.setIdade(request.getIdade());
        triagem.setTelefone(request.getTelefone());
        triagem.setClinicaLaboratorio(request.getClinicaLaboratorio());
        triagem.setAceitoTermos(request.getAceitoTermos());

        triagemRepository.save(triagem);

        List<TriagemResponse.Opcao> opcoes = Arrays.asList(
                new TriagemResponse.Opcao("PRESENCIAL", "Agendamento presencial"),
                new TriagemResponse.Opcao("PRE_GUIA_DIGITAL", "Pré-guia digital")
        );

        return new TriagemResponse("Triagem preenchida com sucesso", opcoes);
    }

    private SolicitacaoMedica validarSolicitacao(String token) {
        return solicitacaoRepository.findByTokenPublico(token)
                .filter(SolicitacaoMedica::getValida)
                .orElseThrow(() -> new SolicitacaoNaoEncontradaException(
                        "Solicitação não encontrada ou inválida"));
    }
}
