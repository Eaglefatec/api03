package com.eagle.fusex.solicitacao.service;

import com.eagle.fusex.medico.dto.MedicoResumoResponse;
import com.eagle.fusex.ocs.OcsProcedimento;
import com.eagle.fusex.ocs.OcsProcedimentoRepository;
import com.eagle.fusex.ocs.dto.OcsResponse;
import com.eagle.fusex.shared.exception.SolicitacaoNaoEncontradaException;
import com.eagle.fusex.shared.exception.TriagemNaoPreenchidaException;
import com.eagle.fusex.solicitacao.SolicitacaoMedica;
import com.eagle.fusex.solicitacao.SolicitacaoMedicaRepository;
import com.eagle.fusex.solicitacao.SolicitacaoProcedimento;
import com.eagle.fusex.solicitacao.SolicitacaoProcedimentoRepository;
import com.eagle.fusex.solicitacao.TriagemPreGuia;
import com.eagle.fusex.solicitacao.TriagemPreGuiaRepository;
import com.eagle.fusex.solicitacao.dto.PreGuiaConsolidadaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PreGuiaConsolidadaService {

    private final SolicitacaoMedicaRepository solicitacaoRepository;
    private final TriagemPreGuiaRepository triagemRepository;
    private final SolicitacaoProcedimentoRepository solicitacaoProcedimentoRepository;
    private final OcsProcedimentoRepository ocsProcedimentoRepository;

    @Autowired
    public PreGuiaConsolidadaService(SolicitacaoMedicaRepository solicitacaoRepository,
                                     TriagemPreGuiaRepository triagemRepository,
                                     SolicitacaoProcedimentoRepository solicitacaoProcedimentoRepository,
                                     @Autowired(required = false) OcsProcedimentoRepository ocsProcedimentoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.triagemRepository = triagemRepository;
        this.solicitacaoProcedimentoRepository = solicitacaoProcedimentoRepository;
        this.ocsProcedimentoRepository = ocsProcedimentoRepository;
    }

    public PreGuiaConsolidadaService(SolicitacaoMedicaRepository solicitacaoRepository,
                                     TriagemPreGuiaRepository triagemRepository,
                                     SolicitacaoProcedimentoRepository solicitacaoProcedimentoRepository) {
        this(solicitacaoRepository, triagemRepository, solicitacaoProcedimentoRepository, null);
    }

    @Transactional(readOnly = true)
    public PreGuiaConsolidadaResponse consultar(String token) {
        SolicitacaoMedica solicitacao = solicitacaoRepository.findByTokenPublico(token)
                .filter(SolicitacaoMedica::getValida)
                .orElseThrow(() -> new SolicitacaoNaoEncontradaException("Solicitação não encontrada ou inválida"));

        TriagemPreGuia triagem = triagemRepository.findBySolicitacaoMedicaId(solicitacao.getId())
                .orElseThrow(() -> new TriagemNaoPreenchidaException(
                        "Triagem ainda não foi preenchida pelo beneficiário; não é possível consultar a pré-guia"));

        PreGuiaConsolidadaResponse.Beneficiario beneficiario = new PreGuiaConsolidadaResponse.Beneficiario(
                solicitacao.getPaciente().getNome(),
                triagem.getIdade(),
                triagem.getCpfPrec(),
                triagem.getTelefone()
        );

        OcsResponse ocsResponse = OcsResponse.from(triagem.getOcs());

        MedicoResumoResponse medicoEmissor = MedicoResumoResponse.from(solicitacao.getMedico());
        MedicoResumoResponse medicoResponsavel = MedicoResumoResponse.from(solicitacao.getMedicoResponsavel());

        List<PreGuiaConsolidadaResponse.ProcedimentoQuantidadeInfo> procedimentos =
                solicitacaoProcedimentoRepository.findBySolicitacaoMedicaId(solicitacao.getId()).stream()
                        .map(vinculo -> toProcedimentoQuantidadeInfo(vinculo, triagem))
                        .toList();

        PreGuiaConsolidadaResponse.InformacoesGerais informacoesGerais = new PreGuiaConsolidadaResponse.InformacoesGerais(
                solicitacao.getDataCriacao(),
                solicitacao.getPaciente().getOm()
        );

        return new PreGuiaConsolidadaResponse(
                beneficiario, ocsResponse, medicoEmissor, medicoResponsavel, procedimentos, informacoesGerais
        );
    }

    private PreGuiaConsolidadaResponse.ProcedimentoQuantidadeInfo toProcedimentoQuantidadeInfo(
            SolicitacaoProcedimento vinculo, TriagemPreGuia triagem) {
        BigDecimal valor = vinculo.getValorNoMomento();
        if (valor == null && ocsProcedimentoRepository != null && triagem != null && triagem.getOcs() != null) {
            valor = ocsProcedimentoRepository
                    .findByOcs_OcsIdAndProcedimento_ProcCodigoDgp(triagem.getOcs().getOcsId(), vinculo.getProcedimento().getProcCodigoDgp())
                    .map(OcsProcedimento::getValor)
                    .orElse(null);
        }

        return new PreGuiaConsolidadaResponse.ProcedimentoQuantidadeInfo(
                vinculo.getProcedimento().getProcCodigoDgp(),
                vinculo.getProcedimento().getProcDescricao(),
                vinculo.getQuantidade(),
                valor
        );
    }
}
