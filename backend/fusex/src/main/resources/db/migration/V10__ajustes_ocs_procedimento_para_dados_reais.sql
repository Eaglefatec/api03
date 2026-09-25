-- Limpa o seed de teste (placeholders "Rua Placeholder", "Contato Pendente" etc.)
-- para abrir espaco para os dados reais importados pelos loaders no startup.
DELETE FROM ocs_procedimento;
DELETE FROM ocs;

-- ===== Ocs: novos campos de contrato/vigencia (armazenados, sem regra ativa) =====
ALTER TABLE ocs ADD COLUMN ocs_tipo VARCHAR(10) NOT NULL DEFAULT 'OCS';
ALTER TABLE ocs ADD COLUMN ocs_contrato_numero VARCHAR(50) NULL;
ALTER TABLE ocs ADD COLUMN ocs_inicio_vigencia DATE NULL;
ALTER TABLE ocs ADD COLUMN ocs_termino_vigencia DATE NULL;

-- ===== Ocs: dados cadastrais viram opcionais (planilha real nao tem CNPJ/endereco/contato) =====
ALTER TABLE ocs MODIFY COLUMN ocs_inscricao_federal VARCHAR(14) NULL;
ALTER TABLE ocs MODIFY COLUMN ocs_endereco VARCHAR(100) NULL;
ALTER TABLE ocs MODIFY COLUMN ocs_endereco_numero VARCHAR(100) NULL;
ALTER TABLE ocs MODIFY COLUMN ocs_endereco_bairro VARCHAR(100) NULL;
ALTER TABLE ocs MODIFY COLUMN ocs_endereco_cidade VARCHAR(100) NULL;
ALTER TABLE ocs MODIFY COLUMN ocs_endereco_uf VARCHAR(100) NULL;
ALTER TABLE ocs MODIFY COLUMN ocs_endereco_cep VARCHAR(8) NULL;
ALTER TABLE ocs MODIFY COLUMN ocs_contato_nome VARCHAR(100) NULL;
ALTER TABLE ocs MODIFY COLUMN ocs_contato_telefone VARCHAR(100) NULL;

-- ===== Ocs: nome vira a chave natural de negocio (CNPJ nao disponivel na fonte real) =====
ALTER TABLE ocs ADD CONSTRAINT uk_ocs_nome UNIQUE (ocs_nome);

-- ===== Ocs: especialidade unica -> multiplas especialidades (planilha mostra OCS com mais de uma) =====
ALTER TABLE ocs DROP COLUMN ocs_especialidade;

CREATE TABLE ocs_especialidade_item (
    ocs_id BIGINT NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    FOREIGN KEY (ocs_id) REFERENCES ocs(ocs_id),
    PRIMARY KEY (ocs_id, especialidade)
);

-- ===== ocs_procedimento: preco especifico por combinacao OCS x Procedimento =====
ALTER TABLE ocs_procedimento ADD COLUMN valor DECIMAL(10,2) NOT NULL DEFAULT 0;
ALTER TABLE ocs_procedimento ADD COLUMN tabela_referencia VARCHAR(500) NULL;
ALTER TABLE ocs_procedimento ADD COLUMN descricao_grupo VARCHAR(500) NULL;

-- ===== Procedimento: descricao deixa de ser unica (TUSS tem 5677 codigos, nao garante unicidade) =====
ALTER TABLE procedimento DROP INDEX procedimento_unique;
ALTER TABLE procedimento ADD COLUMN proc_origem VARCHAR(20) NOT NULL DEFAULT 'TUSS';
