# Timeline API

API REST em Java com Spring Boot para gerenciamento de registros em timelines pessoais.

## Descrição

A aplicação permite criar, atualizar, listar e excluir registros de forma organizada dentro de timelines vinculadas a usuários. O sistema também oferece autenticação, controle de acesso e persistência em PostgreSQL.

## Arquitetura

O projeto está organizado em camadas:

- Controller: expõe as rotas da API
- Service: contém a lógica de negócio
- Repository: acessa o banco de dados via JPA
- Model/DTO: define as entidades e objetos de transferência de dados

## Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Maven
- Docker
- Docker Compose

## Recursos principais

- Cadastro e login de usuários
- Criação de registros em timelines
- Atualização e exclusão de registros
- Exclusão lógica (soft delete)
- Autenticação JWT
- Endpoints públicos e privados
- Deploy via Docker Compose

## Como executar com Docker

No diretório raiz do projeto, execute:

```bash
docker compose up --build
```

Isso criará e iniciará três serviços:

- `db`: banco PostgreSQL
- `backend`: API Spring Boot
- `frontend`: aplicação React/Vite

## Endpoints principais

- Backend: `http://localhost:8080`
- Frontend: `http://localhost:3000`

## Configuração de banco

O serviço PostgreSQL usa as seguintes variáveis padrão:

- `POSTGRES_DB=timeline_db`
- `POSTGRES_USER=postgres`
- `POSTGRES_PASSWORD=root`

## Observações sobre o Docker

O Dockerfile do backend agora faz o build do projeto Maven dentro da imagem, o que evita a necessidade de gerar o JAR localmente antes de criar o container.

O frontend também é construído dentro do Dockerfile e servido pelo Nginx.

## Estrutura de pastas

- `timeline/`: backend Spring Boot
- `Front-end/timeline/`: frontend React/Vite
- `docker-compose.yml`: orquestração dos serviços

## Comandos úteis

- Subir tudo: `docker compose up --build`
- Parar: `docker compose down`
- Ver logs: `docker compose logs -f`
