# Backlog - Sprint 1

**Período: de 07/09/2026 a 27/09/2026**

**Status da Sprint: Concluída**

## 🎯 Desafio

O desafio consiste em modernizar e digitalizar o processo de emissão e tramitação de guias de exames e procedimentos da FUSEX. Atualmente, o processo é manual e baseado em papel, o que gera riscos de perda de documentos, duplicidade de uso de encaminhamentos por meio de cópias e morosidade no atendimento aos beneficiários.

## 🏅 Solução

A solução digital da FUSEX permitirá o envio eletrônico de encaminhamentos pelos médicos credenciados, a solicitação online de pré-guias pelos beneficiários e a consulta/emissão eficiente das guias pelos Emissores de Guia (EG). Isso eliminará o uso de papel físico, reduzirá fraudes e dará celeridade ao fluxo de atendimento.

## 📋 Sprint Backlog

- Objetivo da Sprint: A primeira SPRINT focará em entregar a infraestrutura base de digitalização de encaminhamentos e pré-guias, permitindo que médicos enviem documentos digitalmente, beneficiários criem pré-guias online e os Emissores de Guia (EG) realizem a consulta e primeira emissão no sistema.
- Meta da Sprint: US-01, US-02, US-03

## 📚 Histórias da Sprint

### ID: US-01

**US-01 | Prioridade: Alta | Sprint: 1 | Story Points: 8 | Status: Concluída**

#### 1. Estrutura Padrão

Como médico, quero enviar meu “Encaminhamento” digitalmente ao sistema da Fusex, para facilitar a disponibilidade do documento.

### ID: US-02

**US-02 | Prioridade: Alta | Sprint: 1 | Story Points: 5 | Status: Concluída**

#### 1. Estrutura Padrão

Como Beneficiário, quero fornecer digitalmente uma pré-guia para a Fusex, para ter mais agilidade na emissão da guia.

### ID: US-03

**US-03 | Prioridade: Alta | Sprint: 1 | Story Points: 8 | Status: Concluída**

#### 1. Estrutura Padrão

Como EG, quero consultar as Pré-guias, para auxiliar o preenchimento da GUIA no sistema.

## Sprint 1: Digitalização de Encaminhamentos e Pré-Guias (US-01, US-02, US-03)

### 🏃‍♂️ DoR (Definition of Ready) — Específico da Sprint

**Itens Gerais**

- A User Story possui título claro, descrição no formato "Como... quero... para..." e objetivo compreendido.
- Há wireframes e/ou protótipos de telas aprovados.
- Critérios de Aceite e regras de negócio documentados e validados com o time.
- Sem dependências bloqueadoras e validação prévia com a equipe.

**Específico da Sprint 1**

- Regras de Credenciamento Mapeadas: A base de validação de médicos credenciados (FUSEX) e exames válidos por OCS está claramente mapeada.
- Padrão de Arquivos Definido: Os formatos aceitos de anexos (PDF, JPG, PNG) e o limite de tamanho de arquivo estão especificados tecnicamente.

### 🏆 DoD (Definition of Done) — Específico da Sprint

**Itens Gerais**

- Código devidamente versionado no Git e revisado pela equipe.
- Casos de uso testados e aprovados pelo time.

**Específico da Sprint 1**

- Validação de Upload e Armazenamento: Os arquivos de encaminhamento foram testados quanto ao armazenamento seguro e rejeição de formatos/tamanhos inválidos.
- Registro de Data/Hora: Garantido o registro automático de timestamp (data/hora) nas solicitações de encaminhamento e pré-guias.
- Controle de Duplicidade: Testada a restrição de que cada encaminhamento/guia não pode ser reutilizado indevidamente.