# Desafio TDD — Event City API

API REST desenvolvida com Java e Spring Boot seguindo a metodologia TDD (Test-Driven Development), como parte do programa **Java Spring Expert** da [DevSuperior](https://devsuperior.com.br).

## Sobre o Projeto

A aplicação gerencia **Cidades** e **Eventos**, expondo endpoints REST para operações de busca, inserção, atualização e deleção, com tratamento de exceções e respostas HTTP customizadas.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.3
- Spring Data JPA / Hibernate
- H2 Database (banco em memória para testes)
- JUnit 5 (testes de integração)
- Maven

## Funcionalidades e Endpoints

### Cities

| Método | Endpoint         | Descrição                                      | Resposta           |
|--------|------------------|------------------------------------------------|--------------------|
| GET    | `/cities`        | Retorna todas as cidades ordenadas por nome    | `200 OK`           |
| POST   | `/cities`        | Insere uma nova cidade                         | `201 Created`      |
| DELETE | `/cities/{id}`   | Remove uma cidade por ID                       | `204 No Content`   |
| DELETE | `/cities/{id}`   | ID inexistente                                 | `404 Not Found`    |
| DELETE | `/cities/{id}`   | ID com eventos associados (dependente)         | `400 Bad Request`  |

### Events

| Método | Endpoint        | Descrição                            | Resposta         |
|--------|-----------------|--------------------------------------|------------------|
| PUT    | `/events/{id}`  | Atualiza um evento existente         | `200 OK`         |
| PUT    | `/events/{id}`  | ID inexistente                       | `404 Not Found`  |

## Critérios Avaliados

- [x] `DELETE /cities/{id}` retorna `404 Not Found` quando id não existir
- [x] `DELETE /cities/{id}` retorna `204 No Content` quando id for independente
- [x] `DELETE /cities/{id}` retorna `400 Bad Request` quando id for dependente
- [x] `POST /cities` insere recurso corretamente
- [x] `GET /cities` retorna recursos ordenados por nome
- [x] `PUT /events/{id}` atualiza recurso quando id existir
- [x] `PUT /events/{id}` retorna `404 Not Found` quando id não existir

## Estrutura do Projeto

```
src/
├── main/
│   └── java/com/devsuperior/bds02/
│       ├── dto/               # CityDTO, EventDTO, StandardError
│       ├── entities/          # City, Event
│       ├── repositories/      # CityRepository, EventRepository
│       ├── resources/         # CityController, EventController
│       └── services/          # CityService, EventService
└── test/
    └── java/com/devsuperior/bds02/controllers/
        ├── CityControllerIT.java
        └── EventControllerIT.java
```

## Como Executar

**Pré-requisitos:** Java 21 e Maven instalados.

```bash
# Clonar o repositório
git clone https://github.com/fabianoqss/Desafio-TDD-Java-Spring-Expert

# Entrar na pasta do projeto
cd Desafio-TDD-Java-Spring-Expert

# Executar a aplicação
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

O console do H2 fica disponível em `http://localhost:8080/h2-console` com as configurações:
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User:** `sa`
- **Password:** *(vazio)*

## Executar os Testes

```bash
./mvnw test
```

## Autor

Fabiano Queiroz — [GitHub](https://github.com/fabianoqss)
