package com.eagle.fusex.config;

import com.eagle.fusex.medico.Medico;
import com.eagle.fusex.medico.MedicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

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

            System.out.println("✅ Médicos de teste inseridos com sucesso!");
        }
    }
}
