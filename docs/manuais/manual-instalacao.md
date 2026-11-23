# SISMED - Manual de Instalação

## Pré-requisitos

- **Docker** (em execução) e **Docker Compose**
- **Git**
- **JDK 21**
- Maven não é necessário (o projeto usa o `mvnw`)
- Portas livres: `3306` (MySQL) e `8080` (aplicação)

## 1. Clonar o projeto

O repositório usa submódulos (backend e frontend), então é preciso `--recurse-submodules`:

```bash
git -c "url.https://github.com/.insteadOf=git@github.com:" clone --recurse-submodules https://github.com/Eaglefatec/api03.git
cd api03/api03-backend/fusex
```

Se as pastas `api03-backend` e `api03-frontend` vierem vazias: `git submodule update --init --recursive`.
Se faltar alguma tela (ex.: Faturamento): `git submodule update --init --remote`.

## 2. Subir o banco (MySQL)

```bash
docker compose down
docker compose up -d --wait
```

Banco `fusex_db`, usuário e senha `spring` / `spring`, porta `3306`.

## 3. Rodar a aplicação

As páginas ficam no repositório do frontend, por isso o comando aponta para a pasta delas (`api03-backend` e `api03-frontend` precisam estar lado a lado).

**Windows (PowerShell)**

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=local" "-Dspring-boot.run.arguments=--spring.web.resources.static-locations=file:../../api03-frontend/fusex/src/main/resources/static/"
```

**Linux / macOS**

```bash
chmod +x mvnw   # só na primeira vez, se der "Permission denied"
./mvnw spring-boot:run -Dspring-boot.run.profiles=local -Dspring-boot.run.arguments="--spring.web.resources.static-locations=file:../../api03-frontend/fusex/src/main/resources/static/"
```

A aplicação está pronta quando aparecer `Started FusexApplication`. Na primeira vez, a inicialização é mais lenta (carga de dados e download do Maven).

## 4. Acessar

- **Portal:** <http://localhost:8080/api/index.html>

| Perfil | Usuário | Senha |
|--------|---------|-------|
| Médico | `medico` | `senha123` |
| Emissor de Guia (EG) | `eg` | `senha123` |
| OCS (clínica) | `ocs` | `senha123` |
| Faturamento | `faturamento` | `senha123` |
| Lisura / Administrador | `admin` | `admin123` |

Usuários de teste, apenas para desenvolvimento.

## 5. Testes, encerrar e reiniciar

| Ação | Comando |
|------|---------|
| Rodar os testes (sem Docker) | `./mvnw test` (Windows: `.\mvnw.cmd test`) |
| Parar a aplicação | `Ctrl + C` no terminal |
| Parar o banco | `docker compose down` |
| Reiniciar com banco limpo (apaga os dados) | `docker compose down -v` e depois `docker compose up -d --wait` |

## Problemas comuns

| Problema | Solução |
|----------|---------|
| Pastas dos submódulos vazias | `git submodule update --init --recursive` |
| `Permission denied (publickey)` ao clonar | Use o comando de clone via HTTPS do passo 1 |
| Página `/api/index.html` dá 404 | Confira o argumento `static-locations` e se `api03-frontend` está ao lado de `api03-backend` |
| Porta `3306` ou `8080` em uso | Encerre o processo que usa a porta e tente de novo |
| Erro de conexão com o banco ao iniciar | `docker compose ps` e refaça o passo 2 |
| Erro do Flyway (*checksum* ou tabela já existe) | Recrie o banco: `docker compose down -v` e suba de novo |
| `release version 21 not supported` | Instale o JDK 21 e ajuste o `JAVA_HOME` |