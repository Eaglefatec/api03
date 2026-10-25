<div align="center">

# **SISMED - SISTEMA INTEGRADO DE SAÚDE MILITAR**
</div>

Projeto desenvolvido pela equipe Eagle do 3º semestre de Banco de Dados da FATEC de São José dos Campos, no contexto da metodologia de ensino Aprendizagem por Projetos Integrados (API), em parceria com o Exército Brasileiro.

---
<div align="center">
  <p>
    <a href="#a-dor">A Dor</a> &nbsp;|&nbsp;
    <a href="#a-solucao">A Solução</a> &nbsp;|&nbsp;
    <a href="#backlog-do-produto">Backlog do Produto</a> &nbsp;|&nbsp;
    <a href="#sprints">Cronograma de Sprints</a>
  </p>
  <p>
    <a href="#tecnologias-utilizadas">Tecnologias Utilizadas</a> &nbsp;|&nbsp;
    <a href="#manual-do-usuario">Manual do Usuário</a> &nbsp;|&nbsp;
    <a href="#autores">Equipe</a>
  </p>
</div>

---
<a id="a-dor"></a>
## 🎯 A Dor 

O processo atual de emissão, utilização e faturamento das guias da FUSEX é **fragmentado, altamente manual e suscetível a inconsistências.** Os principais gargalos incluem:

#### Descentralização e Dependência de Papel:
Informações dispersas entre beneficiários, médicos, FUSEX, OCS e setores de aprovação, exigindo deslocamentos físicos e conferências manuais.
#### Riscos de Fraude e Perda:
Encaminhamentos físicos sujeitos a extravios e reutilizações indevidas (cópias/xerox), além do risco de atendimentos com guias vencidas.
#### Esforço Cognitivo Elevado na Lisura:
No fechamento mensal, o ACM precisa cruzar manualmente faturas, espelhos, guias assinadas e contratos, tornando a identificação de divergências e a aplicação de glosas um processo lento e oneroso.
  
**Em resumo:** O cliente enfrenta um fluxo engessado e sem rastreabilidade, desde a solicitação médica até o faturamento final.

---
<a id="a-solucao"></a>
## 🚀 A Solução 

Desenvolver um **ecossistema digital integrado** para a gestão completa de guias, OCS e faturamento, centralizando os dados e automatizando as etapas críticas do processo. A plataforma atua de ponta a ponta:

#### Digitalização e Autonomia:
Envio eletrônico de encaminhamentos por médicos credenciados, pré-guias online para beneficiários e validação ágil de validade (leitura digital) pelas OCS.
#### Fluxo de Aprovação Desburocratizado:
Assinatura digital por médicos e chefias da FUSEX, eliminando o trânsito físico de papéis e reduzindo o tempo de espera do beneficiário.
#### Lisura Inteligente (incluindo Glosas):
Padronização do recebimento via documentos em PDF importáveis (Fatura, Espelho e Guias) e cruzamento automatizado de dados contra as tabelas contratuais.

**O resultado:** Redução drástica de erros e retrabalho, eliminação de gargalos no faturamento, maior controle sobre as OCS e total rastreabilidade de ponta a ponta.

---

**:link: Clique no link abaixo para visualizar o repositório do Frontend e Backend:**
> 🎨 [Frontend](https://github.com/Eaglefatec/api03-frontend.git)

> ⚙️ [Backend](https://github.com/Eaglefatec/api03-backend.git)

---
<a id="backlog-do-produto"></a>

## 📋 Backlog do Produto 
<details closed>
<summary>
 Backlog
</summary> <br />

| Sprint | Prioridade | ID | User Story | Estimativa |
| :----: | :--------: | :---: | :-----: | :--------: |
|  **1** |  **Alta**  | **US01** | Como médico, quero enviar minha solicitação médica digitalmente para um sistema na Fusex. | 8 |
|  **1** |  **Alta**  | **US02** | Como Beneficiário, quero fornecer digitalmente uma pré-guia para a Fusex, para ter mais agilidade na emissão da guia. | 5 |
|  **1** |  **Alta** | **US03** | Como EG, quero emitir as guias com auxílio digital, para evitar erros de preenchimento.                                                               | 3 |
|  **2** |  **Média**  | **US07** | Como OCS, quero receber a Guia e checar a validade dela, para não correr o risco de glosa por perda de validade. | 5 |
|  **2** |  **Média**  | **US08** | Como ACM, quero receber digitalmente a guia, a fatura e o espelho para tornar o processo de lisura mais eficiente e menos sujeito a incorreções. | 13 |
|  **2** |  **Média**  | **US09** | Como ACM, quero digitalizar o cruzamento dos dados recebidos da OCS com as condições contratadas, para ter um relatório de divergências automatizado. | 8 |
|  **3** |  **Baixa**  | **US04** | Como EG, quero coletar aprovação das Guias com os responsáveis, para reduzir o tempo atual de validação para menos de três dias. | 5 |
|  **3** |  **Baixa**  | **US05** | Como EG, quero notificar e enviar a Guia APROVADA digitalmente, para que o Beneficiário possa ter sua solicitação prontamente atendida. | 8 |
|  **3** |  **Baixa**  | **US06** | Como Chefe / Médico, quero assinar as guias digitalmente, para tornar esse processo mais rápido e eficiente. | 5 |

[Legendas](docs/Backlog/legendas.md)

### 📍 [DoR e DoD](docs/Backlog/dor-e-dod.md)
### ✅ [Padrão de Commits](docs/Backlog/padrao-de-commits.md)
### 🌿 [Estratégia de Branches](/docs/Backlog/estrategia-de-branches.md)

</details>

---
<div align="center">

<a id="sprints"></a>

## Cronograma de Sprints
| Sprint | Período | Documentação | Demonstração | Status |
|:---:|---|---|---|---|
| 1 | 07/09 - 27/09 | [Ver Documentação](https://docs.google.com/document/d/1eZ3Eu4DxbGu6Xt57trghgvPsKqgVh6zzKEHZEWdSqEE/edit?usp=sharing) | <div align="center">[Ver Vídeo](https://youtu.be/bo2MY-XA9kM)</div> | ✅ |
| 2 | 05/10 - 25/10 | [Ver Documentação]() | <div align="center">[Ver Vídeo]()</div> | 🔓 IN PROGRESS |
| 3 | 02/11 - 22/11 | [Ver Documentação]() | <div align="center">[Ver Vídeo]()</div> | 🔒 TO DO |
</div>

---
<a id="tecnologias-utilizadas"></a>
<div align="center">

## Tecnologias Utilizadas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-%23C71A36.svg?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%234479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-%23000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
</div>

---

<a id="manual-do-usuario"></a>
## 📜 Manual do Usuário

**:link: Clique no link abaixo para visualizar o Manual do Usuário:**  
> TO DO

---

<a id="autores"></a>
<div align="center">

## Equipe

| Nome      | Função          | Contato |
|-----------|-----------------|---------------|
| Fernando Monteiro | Product Owner | <a href="https://github.com/fernandocosta45"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/fernando-m-da-costa-818282401/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"></a> |
| Breno Cefas | Scrum Master | <a href="https://github.com/cefasbreno"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/breno-cefas-7aa909271/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"></a> |
| Breno Andrade | Desenvolvedor | <a href="https://github.com/brenobsa"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/breno-andrade-241a0521b/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a> |
| Erick Faria | Desenvolvedor | <a href="https://github.com/ErickvFaria"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/érick-vinícius-79193b253?utm_source=share_via&utm_content=profile&utm_medium=member_ios"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a> |
| Matheus Ambrósio | Desenvolvedor | <a href="https://github.com/Froguie"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> <a href=""><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a> |
| Rodrigo Pereira | Desenvolvedor | <a href="https://github.com/ropcastr"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"></a> <a href="https://www.linkedin.com/in/rpcastro/"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"></a> |
</div>