package com.eagle.fusex.encaminhamento;

import com.eagle.fusex.medico.Medico;
import com.eagle.fusex.medico.MedicoRepository;
import com.eagle.fusex.shared.exception.MedicoInvalidoException;
import com.eagle.fusex.shared.exception.ArquivoInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EncaminhamentoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private EncaminhamentoRepository encaminhamentoRepository;

    @Mock
    private MultipartFile arquivo;

    private EncaminhamentoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new EncaminhamentoService(medicoRepository, encaminhamentoRepository);
    }

    @Test
    void testEnviarEncaminhamentoComMedicoNaoCredenciado() {
        when(medicoRepository.findByIdAndCredenciadoTrue(1L)).thenReturn(Optional.empty());

        assertThrows(MedicoInvalidoException.class, () -> {
            service.enviarEncaminhamento(1L, arquivo);
        });

        verify(medicoRepository, times(1)).findByIdAndCredenciadoTrue(1L);
    }

    @Test
    void testEnviarEncaminhamentoComArquivoVazio() {
        Medico medico = new Medico("Dr. João", "123456", true);
        when(medicoRepository.findByIdAndCredenciadoTrue(1L)).thenReturn(Optional.of(medico));
        when(arquivo.isEmpty()).thenReturn(true);

        assertThrows(ArquivoInvalidoException.class, () -> {
            service.enviarEncaminhamento(1L, arquivo);
        });
    }

    @Test
    void testEnviarEncaminhamentoComTipoArquivoInvalido() {
        Medico medico = new Medico("Dr. João", "123456", true);
        when(medicoRepository.findByIdAndCredenciadoTrue(1L)).thenReturn(Optional.of(medico));
        when(arquivo.isEmpty()).thenReturn(false);
        when(arquivo.getContentType()).thenReturn("text/plain");
        when(arquivo.getOriginalFilename()).thenReturn("documento.txt");

        assertThrows(ArquivoInvalidoException.class, () -> {
            service.enviarEncaminhamento(1L, arquivo);
        });
    }

    @Test
    void testEnviarEncaminhamentoComArquivoMaiorQue6MB() {
        Medico medico = new Medico("Dr. João", "123456", true);
        when(medicoRepository.findByIdAndCredenciadoTrue(1L)).thenReturn(Optional.of(medico));
        when(arquivo.isEmpty()).thenReturn(false);
        when(arquivo.getContentType()).thenReturn("application/pdf");
        when(arquivo.getOriginalFilename()).thenReturn("documento.pdf");
        when(arquivo.getSize()).thenReturn(7 * 1024 * 1024L);

        assertThrows(ArquivoInvalidoException.class, () -> {
            service.enviarEncaminhamento(1L, arquivo);
        });
    }
}
