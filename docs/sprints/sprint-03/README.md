# Backlog – Sprint 3

**Período: de 02/11/2026 a 22/11/2026**

**Status da Sprint: A iniciar**

## 🎯 Desafio

O desafio consiste em reduzir o tempo atual de até 3 dias que o beneficiário precisa aguardar para a emissão e aprovação final da guia de procedimento. O modelo atual exige deslocamentos físicos para entrega e retirada de documentos, além do transporte físico de papéis entre setores para coleta de assinaturas de chefia e médicos da FUSEX.

## 🏅 Solução

A solução automatizará o fluxo de aprovação e autorização das guias por meio de assinatura digital para Médicos e Chefes da FUSEX, eliminando o trâmite físico de papéis. Além disso, disponibilizará as guias aprovadas diretamente no painel online do beneficiário e via e-mail, reduzindo o tempo de entrega e a necessidade de atendimento presencial na FUSEX.

## 📋 Sprint Backlog

- Objetivo da Sprint: A terceira SPRINT focará em entregar a esteira digital de assinatura, aprovação e notificação das guias, integrando a validação dos Chefes/Médicos da FUSEX com o envio automático do documento final aprovado diretamente ao beneficiário.
- Meta da Sprint: US-04, US-05, US-06

## 📚 Histórias da Sprint

### ID: US-04

**US-04 | Prioridade: Baixa | Sprint: 3 | Story Points: 5 | Status: A iniciar**

#### 1. Estrutura Padrão

Como EG, quero coletar aprovação das Guias com os responsáveis, para reduzir o tempo atual de validação das guias.

### ID: US-05

**US-05 | Prioridade: Baixa | Sprint: 3 | Story Points: 5 | Status: A iniciar**

#### 1. Estrutura Padrão

Como EG, quero notificar e enviar a Guia APROVADA digitalmente, para que o Beneficiário possa ter sua solicitação prontamente atendida.

### ID: US-06

**US-06 | Prioridade: Baixa | Sprint: 3 | Story Points: 8 | Status: A iniciar**

#### 1. Estrutura Padrão

Como Chefe / Médico, quero assinar as guias digitalmente, para tornar esse processo mais rápido e eficiente.

## Sprint 3: Assinatura Digital, Aprovação e Notificação (US-04, US-05, US-06)

### 🏃‍♂️ DoR (Definition of Ready) — Específico da Sprint

**Itens Gerais**

- A User Story possui título claro, descrição no formato "Como... quero... para..." e objetivo compreendido.
- Há wireframes e/ou protótipos de telas aprovados.
- Critérios de Aceite e regras de negócio documentados e validados com o time.
- Sem dependências bloqueadoras e validação prévia com a equipe.

**Específico da Sprint 3**

- Fluxo de Alçada Definido: Os perfis com permissão para assinatura digital (Chefe da FUSEX e Médicos) estão mapeados no sistema de controle de acesso.
- Configuração de Disparo de E-mail: Os documentos em PDFs em e-mail e as credenciais de envio de notificação ao beneficiário estão configurados.

### 🏆 DoD (Definition of Done) — Específico da Sprint

**Itens Gerais**

- Código devidamente versionado no Git e revisado pela equipe.
- Casos de uso testados e aprovados pelo time.

**Específico da Sprint 3**

- Assinatura Digital Funcional: O processo de assinatura digital via checkbox aplica corretamente o carimbo de data, hora e identificação do responsável no PDF da guia.
- Notificação Automática ao Beneficiário: A guia aprovada é disponibilizada no portal do beneficiário e enviada com sucesso por e-mail no momento da conclusão.
- Tratamento de Rejeições: O fluxo de reprovação por parte do chefe da FUSEX foi testado, garantindo o bloqueio do documento e o registro da justificativa.