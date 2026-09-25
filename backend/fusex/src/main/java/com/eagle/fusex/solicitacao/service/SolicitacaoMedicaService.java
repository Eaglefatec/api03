package com.eagle.fusex.solicitacao.service;

import com.eagle.fusex.medico.Medico;
import com.eagle.fusex.medico.MedicoRepository;
import com.eagle.fusex.ocs.Procedimento;
import com.eagle.fusex.ocs.ProcedimentoRepository;
import com.eagle.fusex.paciente.Paciente;
import com.eagle.fusex.paciente.PacienteRepository;
import com.eagle.fusex.shared.exception.MedicoInvalidoException;
import com.eagle.fusex.shared.exception.ProcedimentoInvalidoException;
import com.eagle.fusex.solicitacao.SolicitacaoMedica;
import com.eagle.fusex.solicitacao.SolicitacaoMedicaRepository;
import com.eagle.fusex.solicitacao.SolicitacaoProcedimento;
import com.eagle.fusex.solicitacao.SolicitacaoProcedimentoRepository;
import com.eagle.fusex.solicitacao.dto.CriarSolicitacaoRequest;
import com.eagle.fusex.solicitacao.dto.SolicitacaoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SolicitacaoMedicaService {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final SolicitacaoMedicaRepository solicitacaoRepository;
    private final ProcedimentoRepository procedimentoRepository;
    private final SolicitacaoProcedimentoRepository solicitacaoProcedimentoRepository;

    public SolicitacaoMedicaService(MedicoRepository medicoRepository,
                                    PacienteRepository pacienteRepository,
                                    SolicitacaoMedicaRepository solicitacaoRepository,
                                    ProcedimentoRepository procedimentoRepository,
                                    SolicitacaoProcedimentoRepository solicitacaoProcedimentoRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.solicitacaoRepository = solicitacaoRepository;
        this.procedimentoRepository = procedimentoRepository;
        this.solicitacaoProcedimentoRepository = solicitacaoProcedimentoRepository;
    }

    @Transactional
    public SolicitacaoResponse criar(CriarSolicitacaoRequest request) {
        Medico medico = validarMedico(request.getMedicoId());

        Paciente paciente = pacienteRepository.findByCpfPrec(request.getCpfPrec())
                .orElseGet(() -> criarNovoPaciente(request));

        atualizarDadosPaciente(paciente, request);

        invalidarSolicitacaoAnterior(medico.getId(), paciente.getId());

        SolicitacaoMedica solicitacao = new SolicitacaoMedica();
        solicitacao.setMedico(medico);
        solicitacao.setPaciente(paciente);
        solicitacao.setEspecialidades(request.getEspecialidades());
        solicitacao.setObservacao(request.getObservacao());
        solicitacao.setTokenPublico(UUID.randomUUID().toString());
        solicitacao.setValida(true);

        SolicitacaoMedica salva = solicitacaoRepository.save(solicitacao);

        persistirProcedimentos(salva, request.getProcedimentos());

        String linkBeneficiario = "/solicitacoes/publico/" + salva.getTokenPublico();

        return new SolicitacaoResponse(
                salva.getId(),
                salva.getTokenPublico(),
                linkBeneficiario,
                "Solicitação médica criada com sucesso"
        );
    }

    private Medico validarMedico(Long medicoId) {
        return medicoRepository.findByIdAndCredenciadoTrue(medicoId)
                .orElseThrow(() -> new MedicoInvalidoException("Selecione um médico válido"));
    }

    private Paciente criarNovoPaciente(CriarSolicitacaoRequest request) {
        Paciente novoPaciente = new Paciente(request.getNomePaciente(), request.getCpfPrec(), request.getOm());
        return pacienteRepository.save(novoPaciente);
    }

    private void atualizarDadosPaciente(Paciente paciente, CriarSolicitacaoRequest request) {
        paciente.setNome(request.getNomePaciente());
        paciente.setOm(request.getOm());
        pacienteRepository.save(paciente);
    }

    private void persistirProcedimentos(SolicitacaoMedica solicitacao,
                                        List<CriarSolicitacaoRequest.ProcedimentoQuantidade> procedimentosRequest) {
        List<SolicitacaoProcedimento> vinculos = procedimentosRequest.stream()
                .map(pq -> {
                    Procedimento procedimento = procedimentoRepository.findById(pq.getProcedimentoCodigoDgp())
                            .orElseThrow(() -> new ProcedimentoInvalidoException(
                                    "Procedimento inválido: " + pq.getProcedimentoCodigoDgp()));
                    SolicitacaoProcedimento vinculo = new SolicitacaoProcedimento();
                    vinculo.setSolicitacaoMedica(solicitacao);
                    vinculo.setProcedimento(procedimento);
                    vinculo.setQuantidade(pq.getQuantidade());
                    return vinculo;
                }).toList();

        solicitacaoProcedimentoRepository.saveAll(vinculos);
    }

    private void invalidarSolicitacaoAnterior(Long medicoId, Long pacienteId) {
        Optional<SolicitacaoMedica> anterior = solicitacaoRepository
                .findByMedicoIdAndPacienteIdAndValidaTrue(medicoId, pacienteId);

        anterior.ifPresent(sol -> {
            sol.setValida(false);
            solicitacaoRepository.save(sol);
        });
    }
}
