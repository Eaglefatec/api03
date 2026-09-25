package com.eagle.fusex.solicitacao;

import com.eagle.fusex.medico.Medico;
import com.eagle.fusex.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SolicitacaoMedicaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SolicitacaoMedicaRepository solicitacaoMedicaRepository;

    @Test
    @DisplayName("findByTokenPublico - Deve encontrar solicitação pelo token público")
    void deveEncontrarPorTokenPublico() {
        Medico medico = entityManager.persist(new Medico("Dr. Silva", "CRM111", true));
        Paciente paciente = entityManager.persist(new Paciente("Lucas Alves", "11122233344", "OM01"));

        SolicitacaoMedica solicitacao = new SolicitacaoMedica();
        solicitacao.setMedico(medico);
        solicitacao.setPaciente(paciente);
        solicitacao.setTokenPublico("token-teste-xyz");
        solicitacao.setValida(true);
        entityManager.persist(solicitacao);
        entityManager.flush();

        Optional<SolicitacaoMedica> resultado = solicitacaoMedicaRepository.findByTokenPublico("token-teste-xyz");

        assertTrue(resultado.isPresent());
        assertEquals("token-teste-xyz", resultado.get().getTokenPublico());
        assertEquals("Lucas Alves", resultado.get().getPaciente().getNome());
    }

    @Test
    @DisplayName("findByMedicoIdAndPacienteIdAndValidaTrue - Deve retornar apenas solicitação válida")
    void deveRetornarApenasSolicitacaoValida() {
        Medico medico = entityManager.persist(new Medico("Dra. Beatriz", "CRM222", true));
        Paciente paciente = entityManager.persist(new Paciente("Ana Lima", "99988877766", "OM02"));

        SolicitacaoMedica solicitacaoInvalida = new SolicitacaoMedica();
        solicitacaoInvalida.setMedico(medico);
        solicitacaoInvalida.setPaciente(paciente);
        solicitacaoInvalida.setTokenPublico("token-invalido");
        solicitacaoInvalida.setValida(false);
        entityManager.persist(solicitacaoInvalida);

        SolicitacaoMedica solicitacaoValida = new SolicitacaoMedica();
        solicitacaoValida.setMedico(medico);
        solicitacaoValida.setPaciente(paciente);
        solicitacaoValida.setTokenPublico("token-valido");
        solicitacaoValida.setValida(true);
        entityManager.persist(solicitacaoValida);
        entityManager.flush();

        Optional<SolicitacaoMedica> resultado = solicitacaoMedicaRepository
                .findByMedicoIdAndPacienteIdAndValidaTrue(medico.getId(), paciente.getId());

        assertTrue(resultado.isPresent());
        assertEquals("token-valido", resultado.get().getTokenPublico());
        assertTrue(resultado.get().getValida());
    }

    @Test
    @DisplayName("findByMedicoIdAndPacienteIdAndValidaTrue - Deve retornar vazio quando só houver solicitação inválida")
    void deveRetornarVazioQuandoNaoHouverSolicitacaoValida() {
        Medico medico = entityManager.persist(new Medico("Dr. Roberto", "CRM333", true));
        Paciente paciente = entityManager.persist(new Paciente("Marcos Souza", "55544433322", "OM03"));

        SolicitacaoMedica solicitacao = new SolicitacaoMedica();
        solicitacao.setMedico(medico);
        solicitacao.setPaciente(paciente);
        solicitacao.setTokenPublico("token-antigo");
        solicitacao.setValida(false);
        entityManager.persist(solicitacao);
        entityManager.flush();

        Optional<SolicitacaoMedica> resultado = solicitacaoMedicaRepository
                .findByMedicoIdAndPacienteIdAndValidaTrue(medico.getId(), paciente.getId());

        assertTrue(resultado.isEmpty());
    }
}
