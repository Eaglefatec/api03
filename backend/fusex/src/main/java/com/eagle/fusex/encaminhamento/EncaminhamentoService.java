package com.eagle.fusex.encaminhamento;

import com.eagle.fusex.medico.Medico;
import com.eagle.fusex.medico.MedicoRepository;
import com.eagle.fusex.shared.exception.MedicoInvalidoException;
import com.eagle.fusex.shared.exception.ArquivoInvalidoException;
import com.eagle.fusex.encaminhamento.dto.EncaminhamentoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EncaminhamentoService {

    private final MedicoRepository medicoRepository;
    private final EncaminhamentoRepository encaminhamentoRepository;
    private final String uploadDir = "uploads";

    public EncaminhamentoService(MedicoRepository medicoRepository, EncaminhamentoRepository encaminhamentoRepository) {
        this.medicoRepository = medicoRepository;
        this.encaminhamentoRepository = encaminhamentoRepository;
        criarDiretorioUpload();
    }

    public EncaminhamentoResponse enviarEncaminhamento(Long medicoId, MultipartFile arquivo) {
        Medico medico = validarMedico(medicoId);
        validarArquivo(arquivo);

        LocalDateTime dataHora = LocalDateTime.now();
        String nomeArquivoOriginal = arquivo.getOriginalFilename();
        String extensao = extrairExtensao(nomeArquivoOriginal);
        String nomeArquivoUnico = UUID.randomUUID() + "." + extensao;
        String caminhoArquivo = uploadDir + "/" + nomeArquivoUnico;

        try {
            Path path = Paths.get(caminhoArquivo);
            Files.write(path, arquivo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo", e);
        }

        Encaminhamento encaminhamento = new Encaminhamento();
        encaminhamento.setMedico(medico);
        encaminhamento.setDataHora(dataHora);
        encaminhamento.setNomeArquivo(nomeArquivoOriginal);
        encaminhamento.setCaminhoArquivo(caminhoArquivo);
        encaminhamento.setTipoArquivo(arquivo.getContentType());
        encaminhamento.setTamanhoBytes(arquivo.getSize());
        encaminhamento.setStatus(StatusEncaminhamento.ENVIADO);

        Encaminhamento salvo = encaminhamentoRepository.save(encaminhamento);

        return new EncaminhamentoResponse(
                salvo.getId(),
                salvo.getDataHora(),
                "Encaminhamento recebido com sucesso"
        );
    }

    private Medico validarMedico(Long medicoId) {
        return medicoRepository.findByIdAndCredenciadoTrue(medicoId)
                .orElseThrow(() -> new MedicoInvalidoException("Selecione um médico válido"));
    }

    private void validarArquivo(MultipartFile arquivo) {
        if (arquivo.isEmpty()) {
            throw new ArquivoInvalidoException("Carregue o encaminhamento em PDF, JPG ou PNG");
        }

        String nomeArquivo = arquivo.getOriginalFilename();
        long tamanho = arquivo.getSize();

        System.out.println("DEBUG: Nome do arquivo = " + nomeArquivo);
        System.out.println("DEBUG: Tamanho = " + tamanho);
        System.out.println("DEBUG: Content-Type = " + arquivo.getContentType());

        // Validar extensão (mais leniente)
        String nomeMinusculo = nomeArquivo != null ? nomeArquivo.toLowerCase() : "";
        boolean extensaoValida = nomeMinusculo.endsWith(".pdf") ||
                                nomeMinusculo.endsWith(".jpg") ||
                                nomeMinusculo.endsWith(".jpeg") ||
                                nomeMinusculo.endsWith(".png");

        System.out.println("DEBUG: Extensão válida = " + extensaoValida);

        if (!extensaoValida) {
            throw new ArquivoInvalidoException("Carregue o encaminhamento em PDF, JPG ou PNG");
        }

        // Validar tamanho (6MB = 6291456 bytes)
        if (tamanho > 6 * 1024 * 1024) {
            throw new ArquivoInvalidoException("Carregue o encaminhamento em PDF, JPG ou PNG");
        }
    }

    private String extrairExtensao(String nomeArquivo) {
        if (nomeArquivo == null || !nomeArquivo.contains(".")) {
            return "bin";
        }
        return nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1).toLowerCase();
    }

    private void criarDiretorioUpload() {
        try {
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar diretório de uploads", e);
        }
    }
}
