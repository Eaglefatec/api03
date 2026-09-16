package com.eagle.fusex.solicitacao.service;

import com.eagle.fusex.medico.Medico;
import com.eagle.fusex.medico.MedicoRepository;
import com.eagle.fusex.paciente.Paciente;
import com.eagle.fusex.paciente.PacienteRepository;
import com.eagle.fusex.shared.exception.MedicoInvalidoException;
import com.eagle.fusex.solicitacao.SolicitacaoMedica;
import com.eagle.fusex.solicitacao.SolicitacaoMedicaRepository;
import com.eagle.fusex.solicitacao.dto.CriarSolicitacaoRequest;
import com.eagle.fusex.solicitacao.dto.SolicitacaoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class SolicitacaoMedicaService {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final SolicitacaoMedicaRepository solicitacaoRepository;

    public SolicitacaoMedicaService(MedicoRepository medicoRepository,
                                    PacienteRepository pacienteRepository,
                                    SolicitacaoMedicaRepository solicitacaoRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.solicitacaoRepository = solicitacaoRepository;
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

    private void invalidarSolicitacaoAnterior(Long medicoId, Long pacienteId) {
        Optional<SolicitacaoMedica> anterior = solicitacaoRepository
                .findByMedicoIdAndPacienteIdAndValidaTrue(medicoId, pacienteId);

        anterior.ifPresent(sol -> {
            sol.setValida(false);
            solicitacaoRepository.save(sol);
        });
    }
}
