package com.eagle.fusex.solicitacao.service;

import com.eagle.fusex.ocs.Ocs;
import com.eagle.fusex.ocs.OcsProcedimento;
import com.eagle.fusex.ocs.OcsProcedimentoRepository;
import com.eagle.fusex.ocs.OcsRepository;
import com.eagle.fusex.shared.exception.OcsInvalidoException;
import com.eagle.fusex.shared.exception.SolicitacaoNaoEncontradaException;
import com.eagle.fusex.shared.exception.TriagemJaPreenchidaException;
import com.eagle.fusex.solicitacao.SolicitacaoMedica;
import com.eagle.fusex.solicitacao.SolicitacaoMedicaRepository;
import com.eagle.fusex.solicitacao.SolicitacaoProcedimento;
import com.eagle.fusex.solicitacao.SolicitacaoProcedimentoRepository;
import com.eagle.fusex.solicitacao.TriagemPreGuia;
import com.eagle.fusex.solicitacao.TriagemPreGuiaRepository;
import com.eagle.fusex.solicitacao.dto.PreencherTriagemRequest;
import com.eagle.fusex.solicitacao.dto.SolicitacaoPublicaResponse;
import com.eagle.fusex.solicitacao.dto.TriagemResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TriagemPreGuiaService {

    private final SolicitacaoMedicaRepository solicitacaoRepository;
    private final TriagemPreGuiaRepository triagemRepository;
    private final OcsRepository ocsRepository;
    private final SolicitacaoProcedimentoRepository solicitacaoProcedimentoRepository;
    private final OcsProcedimentoRepository ocsProcedimentoRepository;

    public TriagemPreGuiaService(SolicitacaoMedicaRepository solicitacaoRepository,
                                 TriagemPreGuiaRepository triagemRepository,
                                 OcsRepository ocsRepository,
                                 SolicitacaoProcedimentoRepository solicitacaoProcedimentoRepository,
                                 OcsProcedimentoRepository ocsProcedimentoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.triagemRepository = triagemRepository;
        this.ocsRepository = ocsRepository;
        this.solicitacaoProcedimentoRepository = solicitacaoProcedimentoRepository;
        this.ocsProcedimentoRepository = ocsProcedimentoRepository;
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

        Ocs ocs = ocsRepository.findById(request.getOcsId())
                .orElseThrow(() -> new OcsInvalidoException("Selecione uma clínica/OCS válida"));

        TriagemPreGuia triagem = new TriagemPreGuia();
        triagem.setSolicitacaoMedica(solicitacao);
        triagem.setCpfPrec(request.getCpfPrec());
        triagem.setIdade(request.getIdade());
        triagem.setTelefone(request.getTelefone());
        triagem.setOcs(ocs);
        triagem.setAceitoTermos(request.getAceitoTermos());

        triagemRepository.save(triagem);

        snapshotValoresProcedimentos(solicitacao, ocs);

        List<TriagemResponse.Opcao> opcoes = Arrays.asList(
                new TriagemResponse.Opcao("PRESENCIAL", "Agendamento presencial"),
                new TriagemResponse.Opcao("PRE_GUIA_DIGITAL", "Pré-guia digital")
        );

        return new TriagemResponse("Triagem preenchida com sucesso", opcoes);
    }

    private void snapshotValoresProcedimentos(SolicitacaoMedica solicitacao, Ocs ocs) {
        List<SolicitacaoProcedimento> vinculos = solicitacaoProcedimentoRepository
                .findBySolicitacaoMedicaId(solicitacao.getId());

        for (SolicitacaoProcedimento vinculo : vinculos) {
            Optional<OcsProcedimento> ocsProcedimento = ocsProcedimentoRepository
                    .findByOcs_OcsIdAndProcedimento_ProcCodigoDgp(ocs.getOcsId(), vinculo.getProcedimento().getProcCodigoDgp());

            ocsProcedimento.ifPresent(op -> {
                vinculo.setValorNoMomento(op.getValor());
                solicitacaoProcedimentoRepository.save(vinculo);
            });
        }
    }

    private SolicitacaoMedica validarSolicitacao(String token) {
        return solicitacaoRepository.findByTokenPublico(token)
                .filter(SolicitacaoMedica::getValida)
                .orElseThrow(() -> new SolicitacaoNaoEncontradaException(
                        "Solicitação não encontrada ou inválida"));
    }
}
