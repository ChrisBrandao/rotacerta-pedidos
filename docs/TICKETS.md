# Sprint 0 — Onboarding e nivelamento
**Período:** 23/11/2026 (seg) a 04/12/2026 (sex)
**Responsável:** Christophe Brandão (Estagiário I)
**Tech Lead:** Marcos Vieira

> Prioridade: Crítica > Alta > Média > Baixa. Faça na ordem, a não ser que esteja bloqueado.

---

## RC-100 · [Onboarding] Subir o projeto localmente
**Prioridade:** Crítica · **Prazo:** 23/11 (seg) · **Estimativa:** 1 pt

- Clonar/abrir o projeto, subir com `mvn spring-boot:run`
- Criar um pedido via curl/Postman/Insomnia e consultá-lo
- Abrir o console H2 e achar as tabelas
- Rodar `mvn test` e anotar o que acontece

**Entrega:** mensagem no canal do time dizendo o que funcionou, o que não funcionou e o print/saída do teste.

---

## RC-101 · [Bug] Valor total do pedido errado com quantidade > 1
**Prioridade:** Crítica · **Prazo:** 25/11 (qua) · **Estimativa:** 1 pt
**Reportado por:** Financeiro

> "O Mercadinho Bom Preço pediu 3 fitas de R$ 4,50 e 2 rolos de etiqueta de R$ 25,00. A nota saiu R$ 29,50. Era pra ser R$ 63,50. A gente está cobrando menos de todo mundo."

**Critérios de aceite**
- Valor total respeita RN-01
- O teste `deveCalcularValorTotalConsiderandoQuantidade` passa
- CI verde

**Bônus (conta na avaliação):** a responsabilidade de calcular o total deveria estar no service? Proponha (e justifique) onde ela mora melhor.

---

## RC-102 · [Bug] Pedido inexistente retorna 500
**Prioridade:** Alta · **Prazo:** 27/11 (sex) · **Estimativa:** 2 pts
**Reportado por:** Time do app mobile

> "Quando o motorista digita um código de pedido errado, o app mostra 'Erro interno do servidor'. Esperávamos 404 pra mostrar 'pedido não encontrado'."

**Critérios de aceite**
- `GET /api/pedidos/{id}` e `PATCH /api/pedidos/{id}/status` com id inexistente → 404
- `POST /api/pedidos` com `clienteId` inexistente → 404 (ou 422, **justifique a escolha no PR**)
- Corpo de erro no formato RN-04, implementado de forma centralizada (nada de try/catch em cada endpoint)
- Erro de validação (400) lista os campos inválidos
- Testes cobrindo os cenários

---

## RC-103 · [Bug] Pedido cancelado está sendo despachado
**Prioridade:** Alta · **Prazo:** 02/12 (qua) · **Estimativa:** 3 pts
**Reportado por:** Operações

> "Ontem um caminhão saiu com carga de um pedido que o cliente tinha cancelado. O sistema deixou mudar de CANCELADO pra EM_TRANSPORTE."

**Critérios de aceite**
- Somente as transições da RN-02 são aceitas
- Transição inválida → 422 com mensagem tipo `"Transição de CANCELADO para EM_TRANSPORTE não permitida"`
- Testes unitários cobrindo **todas** as transições válidas e pelo menos 3 inválidas
- A regra não pode ficar num `if` gigante no controller

---

## RC-104 · [Feature] Listagem paginada com filtro por status
**Prioridade:** Média · **Prazo:** 04/12 (sex) · **Estimativa:** 3 pts
**Solicitado por:** Operações

> "A tela de expedição carrega todos os pedidos de uma vez e trava. Queremos ver só os PAGO, de 20 em 20, mais antigos primeiro."

**Critérios de aceite**
- `GET /api/pedidos?status=PAGO&page=0&size=20`
- `status` opcional; sem ele, lista todos
- Ordenação padrão: `criadoEm` ascendente
- `size` máximo 100
- Teste de integração (`@SpringBootTest` ou `@DataJpaTest` + `@WebMvcTest`) cobrindo o filtro

---

## Backlog (não puxar ainda)
- RC-105 · [Tech debt] Controller expõe entidades JPA diretamente — criar DTOs de resposta
- RC-106 · [Infra] Migrar H2 → PostgreSQL com Flyway e Docker Compose
- RC-107 · [Infra] Pipeline de CI no GitHub Actions
