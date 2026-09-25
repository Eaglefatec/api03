package com.eagle.fusex.solicitacao.service;

import com.eagle.fusex.medico.Medico;
import com.eagle.fusex.ocs.Ocs;
import com.eagle.fusex.ocs.Procedimento;
import com.eagle.fusex.paciente.Paciente;
import com.eagle.fusex.shared.exception.SolicitacaoNaoEncontradaException;
import com.eagle.fusex.shared.exception.TriagemNaoPreenchidaException;
import com.eagle.fusex.solicitacao.SolicitacaoMedica;
import com.eagle.fusex.solicitacao.SolicitacaoMedicaRepository;
import com.eagle.fusex.solicitacao.SolicitacaoProcedimento;
import com.eagle.fusex.solicitacao.SolicitacaoProcedimentoRepository;
import com.eagle.fusex.solicitacao.TriagemPreGuia;
import com.eagle.fusex.solicitacao.TriagemPreGuiaRepository;
import com.eagle.fusex.solicitacao.dto.PreGuiaConsolidadaResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PreGuiaConsolidadaServiceTest {

    @Mock
    private SolicitacaoMedicaRepository solicitacaoRepository;

    @Mock
    private TriagemPreGuiaRepository triagemRepository;

    @Mock
    private SolicitacaoProcedimentoRepository solicitacaoProcedimentoRepository;

    @InjectMocks
    private PreGuiaConsolidadaService service;

    private SolicitacaoMedica solicitacao;
    private Paciente paciente;
    private Medico medicoEmissor;
    private TriagemPreGuia triagem;
    private Ocs ocs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        paciente = new Paciente("João Silva", "12345678901", "OM001");
        paciente.setId(1L);

        medicoEmissor = new Medico("Dr. João Silva", "123456", true);
        medicoEmissor.setId(1L);

        ocs = new Ocs();
        ocs.setOcsId(1L);
        ocs.setOcsNome("Clínica Teste");

        solicitacao = new SolicitacaoMedica();
        solicitacao.setId(1L);
        solicitacao.setTokenPublico("token-uuid-123");
        solicitacao.setValida(true);
        solicitacao.setPaciente(paciente);
        solicitacao.setMedico(medicoEmissor);
        solicitacao.setDataCriacao(LocalDateTime.now());

        triagem = new TriagemPreGuia();
        triagem.setId(1L);
        triagem.setIdade(30);
        triagem.setCpfPrec("12345678901");
        triagem.setTelefone("11999999999");
        triagem.setOcs(ocs);
    }

    @Test
    void testConsultar_TokenInexistente_LancaException() {
        when(solicitacaoRepository.findByTokenPublico("token-invalido"))
                .thenReturn(Optional.empty());

        assertThrows(SolicitacaoNaoEncontradaException.class,
                () -> service.consultar("token-invalido"));
    }

    @Test
    void testConsultar_TriagemNaoPreenchida_LancaException() {
        when(solicitacaoRepository.findByTokenPublico("token-uuid-123"))
                .thenReturn(Optional.of(solicitacao));
        when(triagemRepository.findBySolicitacaoMedicaId(1L))
                .thenReturn(Optional.empty());

        assertThrows(TriagemNaoPreenchidaException.class,
                () -> service.consultar("token-uuid-123"));
    }

    @Test
    void testConsultar_ComSucesso_MedicoResponsavelNulo() {
        when(solicitacaoRepository.findByTokenPublico("token-uuid-123"))
                .thenReturn(Optional.of(solicitacao));
        when(triagemRepository.findBySolicitacaoMedicaId(1L))
                .thenReturn(Optional.of(triagem));
        when(solicitacaoProcedimentoRepository.findBySolicitacaoMedicaId(1L))
                .thenReturn(List.of());

        PreGuiaConsolidadaResponse response = service.consultar("token-uuid-123");

        assertNotNull(response);
        assertEquals("João Silva", response.getBeneficiario().getNome());
        assertEquals(30, response.getBeneficiario().getIdade());
        assertEquals("Clínica Teste", response.getOcs().getOcsNome());
        assertEquals("Dr. João Silva", response.getMedicoEmissor().getNome());
        assertNull(response.getMedicoResponsavel());
        assertTrue(response.getProcedimentos().isEmpty());
    }

    @Test
    void testConsultar_ComSucesso_MultiplosProcedimentos() {
        Procedimento procedimento1 = new Procedimento("DGP001", "Consulta eletiva", 1);
        Procedimento procedimento2 = new Procedimento("DGP002", "ECG", 1);

        SolicitacaoProcedimento vinculo1 = new SolicitacaoProcedimento();
        vinculo1.setProcedimento(procedimento1);
        vinculo1.setQuantidade(1);

        SolicitacaoProcedimento vinculo2 = new SolicitacaoProcedimento();
        vinculo2.setProcedimento(procedimento2);
        vinculo2.setQuantidade(2);

        when(solicitacaoRepository.findByTokenPublico("token-uuid-123"))
                .thenReturn(Optional.of(solicitacao));
        when(triagemRepository.findBySolicitacaoMedicaId(1L))
                .thenReturn(Optional.of(triagem));
        when(solicitacaoProcedimentoRepository.findBySolicitacaoMedicaId(1L))
                .thenReturn(List.of(vinculo1, vinculo2));

        PreGuiaConsolidadaResponse response = service.consultar("token-uuid-123");

        assertNotNull(response);
        assertEquals(2, response.getProcedimentos().size());
        assertEquals("DGP001", response.getProcedimentos().get(0).getProcedimentoCodigoDgp());
        assertEquals(2, response.getProcedimentos().get(1).getQuantidade());
    }
}
