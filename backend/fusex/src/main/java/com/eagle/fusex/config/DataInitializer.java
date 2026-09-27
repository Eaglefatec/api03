package com.eagle.fusex.config;

import com.eagle.fusex.medico.Medico;
import com.eagle.fusex.medico.MedicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final MedicoRepository medicoRepository;

    public DataInitializer(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (medicoRepository.count() == 0) {
            Medico medico1 = new Medico("Dr. João Silva", "123456", true);
            Medico medico2 = new Medico("Dra. Maria Santos", "654321", false);

            medicoRepository.save(medico1);
            medicoRepository.save(medico2);

            log.info("Médicos de teste inseridos com sucesso!");
        }
    }
}
