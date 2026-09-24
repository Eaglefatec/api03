package com.eagle.fusex.solicitacao.service;

import com.eagle.fusex.medico.Medico;
import com.eagle.fusex.medico.MedicoRepository;
import com.eagle.fusex.paciente.Paciente;
import com.eagle.fusex.paciente.PacienteRepository;
import com.eagle.fusex.shared.exception.MedicoInvalidoException;
import com.eagle.fusex.solicitacao.Especialidade;
import com.eagle.fusex.solicitacao.SolicitacaoMedica;
import com.eagle.fusex.solicitacao.SolicitacaoMedicaRepository;
import com.eagle.fusex.solicitacao.dto.CriarSolicitacaoRequest;
import com.eagle.fusex.solicitacao.dto.SolicitacaoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SolicitacaoMedicaServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private SolicitacaoMedicaRepository solicitacaoRepository;

    @InjectMocks
    private SolicitacaoMedicaService service;

    private Medico medicoCredenciado;
    private Medico medicoNaoCredenciado;
    private Paciente paciente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        medicoCredenciado = new Medico("Dr. João Silva", "123456", true);
        medicoCredenciado.setId(1L);

        medicoNaoCredenciado = new Medico("Dra. Maria Santos", "654321", false);
        medicoNaoCredenciado.setId(2L);

        paciente = new Paciente("João Silva", "12345678901", "OM001");
        paciente.setId(1L);
    }

    @Test
    void testCriarSolicitacao_MedicoNaoCredenciado_LancaException() {
        when(medicoRepository.findByIdAndCredenciadoTrue(2L))
                .thenReturn(Optional.empty());

        CriarSolicitacaoRequest request = new CriarSolicitacaoRequest();
        request.setMedicoId(2L);

        assertThrows(MedicoInvalidoException.class, () -> service.criar(request));
    }

    @Test
    void testCriarSolicitacao_PacienteNovo_CriaComSucesso() {
        Set<Especialidade> especialidades = new HashSet<>();
        especialidades.add(Especialidade.CARDIOLOGISTA);

        CriarSolicitacaoRequest request = new CriarSolicitacaoRequest();
        request.setMedicoId(1L);
        request.setNomePaciente("João Silva");
        request.setCpfPrec("12345678901");
        request.setOm("OM001");
        request.setEspecialidades(especialidades);

        when(medicoRepository.findByIdAndCredenciadoTrue(1L))
                .thenReturn(Optional.of(medicoCredenciado));
        when(pacienteRepository.findByCpfPrec("12345678901"))
                .thenReturn(Optional.empty());
        when(pacienteRepository.save(any(Paciente.class)))
                .thenReturn(paciente);
        when(solicitacaoRepository.findByMedicoIdAndPacienteIdAndValidaTrue(1L, 1L))
                .thenReturn(Optional.empty());

        SolicitacaoMedica solicitacaoSalva = new SolicitacaoMedica();
        solicitacaoSalva.setId(1L);
        solicitacaoSalva.setTokenPublico("token-uuid");

        when(solicitacaoRepository.save(any(SolicitacaoMedica.class)))
                .thenReturn(solicitacaoSalva);

        SolicitacaoResponse response = service.criar(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("token-uuid", response.getTokenPublico());
        assertTrue(response.getLinkBeneficiario().contains("/solicitacoes/publico/"));
    }

    @Test
    void testCriarSolicitacao_PacienteExistente_AtualizaDados() {
        Set<Especialidade> especialidades = new HashSet<>();
        especialidades.add(Especialidade.ORTOPEDISTA);

        CriarSolicitacaoRequest request = new CriarSolicitacaoRequest();
        request.setMedicoId(1L);
        request.setNomePaciente("João Silva Atualizado");
        request.setCpfPrec("12345678901");
        request.setOm("OM002");
        request.setEspecialidades(especialidades);

        when(medicoRepository.findByIdAndCredenciadoTrue(1L))
                .thenReturn(Optional.of(medicoCredenciado));
        when(pacienteRepository.findByCpfPrec("12345678901"))
                .thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any(Paciente.class)))
                .thenReturn(paciente);
        when(solicitacaoRepository.findByMedicoIdAndPacienteIdAndValidaTrue(1L, 1L))
                .thenReturn(Optional.empty());

        SolicitacaoMedica solicitacaoSalva = new SolicitacaoMedica();
        solicitacaoSalva.setId(2L);
        solicitacaoSalva.setTokenPublico("token-uuid-2");

        when(solicitacaoRepository.save(any(SolicitacaoMedica.class)))
                .thenReturn(solicitacaoSalva);

        SolicitacaoResponse response = service.criar(request);

        assertNotNull(response);
        verify(pacienteRepository).save(any(Paciente.class));
    }

    @Test
    void testCriarSolicitacao_SolicitacaoAnteriorExiste_InvalidaAnterior() {
        Set<Especialidade> especialidades = new HashSet<>();
        especialidades.add(Especialidade.CARDIOLOGISTA);

        CriarSolicitacaoRequest request = new CriarSolicitacaoRequest();
        request.setMedicoId(1L);
        request.setNomePaciente("João Silva");
        request.setCpfPrec("12345678901");
        request.setOm("OM001");
        request.setEspecialidades(especialidades);

        SolicitacaoMedica solicitacaoAnterior = new SolicitacaoMedica();
        solicitacaoAnterior.setId(99L);
        solicitacaoAnterior.setValida(true);

        when(medicoRepository.findByIdAndCredenciadoTrue(1L))
                .thenReturn(Optional.of(medicoCredenciado));
        when(pacienteRepository.findByCpfPrec("12345678901"))
                .thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any(Paciente.class)))
                .thenReturn(paciente);
        when(solicitacaoRepository.findByMedicoIdAndPacienteIdAndValidaTrue(1L, 1L))
                .thenReturn(Optional.of(solicitacaoAnterior));

        SolicitacaoMedica solicitacaoNova = new SolicitacaoMedica();
        solicitacaoNova.setId(3L);
        solicitacaoNova.setTokenPublico("token-uuid-3");

        when(solicitacaoRepository.save(any(SolicitacaoMedica.class)))
                .thenReturn(solicitacaoNova);

        SolicitacaoResponse response = service.criar(request);

        assertNotNull(response);
        verify(solicitacaoRepository, times(2)).save(any(SolicitacaoMedica.class));
    }
}
