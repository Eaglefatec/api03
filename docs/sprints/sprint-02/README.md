# Backlog – Sprint 2

**Período: de 05/10/2026 a 25/10/2026**

**Status da Sprint: Em andamento**

## 🎯 Desafio

O desafio consiste em eliminar a inconsistência e o grande esforço cognitivo na validação das guias nas OCS (Organizações Civis de Saúde) e na lisura realizada pelo ACM. Atualmente, os documentos chegam de forma heterogênea (planilhas e papéis), o que gera riscos de atendimento com guias vencidas, glosas não identificadas e divergências nos valores faturados.

## 🏅 Solução

A solução proverá um mecanismo de verificação visual/digital de validade de guias para as OCS, padronização no recebimento de faturas, espelhos e guias assinadas por meio de documentos em formato PDF importáveis, e um algoritmo de cruzamento automatizado de dados com as tabelas contratuais para geração automática de relatórios de divergência e gestão de glosas.

## 📋 Sprint Backlog

- Objetivo da Sprint: A segunda SPRINT focará em entregar as funcionalidades voltadas às OCS e trabalho de lisura do ACM, garantindo a verificação de validade de guias na ponta do atendimento, a padronização no envio de faturas/espelhos e a automação do cruzamento de dados para detecção de divergências.
- Meta da Sprint: US-07, US-08, US-09

## 📚 Histórias da Sprint

### ID: US-07

**US-07 | Prioridade: Média | Sprint: 2 | Story Points: 5 | Status: Em andamento**

#### 1. Estrutura Padrão

Como OCS, quero receber a Guia e checar a validade dela, para não correr o risco de glosa por perda de validade.

### ID: US-08

**US-08 | Prioridade: Média | Sprint: 2 | Story Points: 13 | Status: Em andamento**

#### 1. Estrutura Padrão

Como ACM, quero receber digitalmente a guia, a fatura e o espelho, para tornar o processo de lisura mais eficiente e menos sujeito a incorreções.

### ID: US-09

**US-09 | Prioridade: Média | Sprint: 2 | Story Points: 13 | Status: Em andamento**

#### 1. Estrutura Padrão

Como ACM, quero digitalizar o cruzamento dos dados recebidos da OCS com as condições contratadas, para ter um relatório de divergências automatizado.

## Sprint 2: Gestão OCS, Documentos em formato PDF e Lisura/Glosa (US-07, US-08, US-09)

### 🏃‍♂️ DoR (Definition of Ready) — Específico da Sprint

**Itens Gerais**

- A User Story possui título claro, descrição no formato "Como... quero... para..." e objetivo compreendido.
- Há wireframes e/ou protótipos de telas aprovados.
- Critérios de Aceite e regras de negócio documentados e validados com o time.
- Sem dependências bloqueadoras e validação prévia com a equipe.

**Específico da Sprint 2**

- Layout do Documento Definido: O layout padrão do documento em PDF importável (contendo Fatura, Espelho e Guias-Assinadas) está estruturado e documentado.
- Tabela de Preços Mapeada: As regras da tabela de preços por serviço x OCS estão disponíveis para cruzamento de dados.

### 🏆 DoD (Definition of Done) — Específico da Sprint

**Itens Gerais**

- Código devidamente versionado no Git e revisado pela equipe.
- Casos de uso testados e aprovados pelo time.

**Específico da Sprint 2**

- Leitura de Validade por Código: O mecanismo de checagem de validade e alertas visuais (verde/vermelho) foi validado no atendimento da OCS.
- Importação de Lotes: O parser/importador de documentos processa corretamente arquivos em lote, validando a presença obrigatória da "Guia-Assinada".
- Motor de Cruzamento e Glosas: O algoritmo de auditoria cruza com sucesso os dados do espelho com as regras contratuais, gerando o relatório de divergências e permitindo o envio da glosa.