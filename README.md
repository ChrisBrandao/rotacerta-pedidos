# rotacerta-pedidos

API de pedidos e entregas da **Rota Certa Logística** (Região dos Lagos - RJ).
Atende mercados, farmácias e lojas de material de construção que despacham mercadoria pela nossa frota.

## Stack
- Java 21
- Spring Boot 3.5 (Web, Data JPA, Validation)
- H2 em memória (ambiente de dev) — PostgreSQL está no roadmap
- JUnit 5 + Mockito + AssertJ

## Como rodar
```bash
mvn spring-boot:run
```
- API: http://localhost:8080/api/pedidos
- Console H2: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:rotacerta`, user `sa`, sem senha)

Clientes de exemplo são carregados por `src/main/resources/data.sql` (ids 1, 2 e 3).

## Testes
```bash
mvn test
```
> ⚠️ O CI está vermelho no momento. Ver ticket **RC-101**.

## Endpoints atuais
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/pedidos` | Cria pedido |
| GET | `/api/pedidos` | Lista todos os pedidos |
| GET | `/api/pedidos/{id}` | Busca pedido por id |
| PATCH | `/api/pedidos/{id}/status` | Atualiza status |

### Exemplo de criação
```bash
curl -X POST localhost:8080/api/pedidos -H "Content-Type: application/json" -d '{
  "clienteId": 1,
  "itens": [
    {"descricao": "Fita adesiva", "quantidade": 3, "precoUnitario": 4.50},
    {"descricao": "Etiqueta termica (rolo)", "quantidade": 2, "precoUnitario": 25.00}
  ]
}'
```

## Documentação interna
- `docs/TICKETS.md` — backlog do sprint
- `docs/REGRAS_DE_NEGOCIO.md` — regras que o código PRECISA respeitar
- `docs/PROCESSO.md` — como entregar trabalho nesta empresa
