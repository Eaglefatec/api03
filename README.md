# **SISMED - SISTEMA INTEGRADO DE SAÚDE MILITAR**

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
O processo atual de emissão, utilização e faturamento das guias envolve diversas etapas manuais e consultas realizadas separadamente pelos funcionários do FUSEX.

Além disso, as informações são movimentadas entre diferentes envolvidos (beneficiário, FUSEX, OCS e setores responsáveis pela análise e aprovação gerando a necessidade de conferências e lançamentos manuais). No faturamento, o gestor precisa conferir a guia, a fatura e o espelho, verificando procedimentos, valores, materiais e contratos, além de identificar e corrigir possíveis divergências por meio de glosas.

Em resumo: o cliente enfrenta um processo fragmentado, manual e sujeito a inconsistências, com dificuldade para acompanhar todas as etapas do atendimento, desde a solicitação médica até o faturamento e a auditoria.

---
<a id="a-solucao"></a>
## 🚀 A Solução 

Desenvolver um sistema integrado para gestão de guias, OCS e faturamento, centralizando as informações e automatizando as principais etapas do processo. O sistema permitirá acompanhar a solicitação desde o pedido médico, passando pela autorização, emissão da guia, atendimento na OCS, assinatura do beneficiário, envio do faturamento e auditoria, até a integração com o SisFat.

Com a centralização das informações, o sistema busca reduzir erros de preenchimento, agilizar a emissão das guias, facilitar o controle das OCS, diminuir inconsistências no faturamento e garantir maior rastreabilidade de todo o processo.

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