package com.eagle.fusex.shared.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String salvar(MultipartFile arquivo, String nomeDestino);

    String salvar(byte[] conteudo, String nomeDestino);
}
