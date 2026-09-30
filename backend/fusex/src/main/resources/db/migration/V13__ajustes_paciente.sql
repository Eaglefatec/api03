ALTER TABLE paciente (
    RENAME COLUMN cpf_prec TO cpf,
    ADD COLUMN prec VARCHAR(20) NOT NULL UNIQUE
    );