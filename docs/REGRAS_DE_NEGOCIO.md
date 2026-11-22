# Regras de negócio — Pedidos

Documento mantido pelo time de Produto. Em caso de dúvida, **pergunte antes de inventar regra**.

## RN-01 — Valor total
O valor total do pedido é a soma de `quantidade × precoUnitario` de cada item.
Valores monetários usam `BigDecimal` com 2 casas decimais. Nunca `double`.

## RN-02 — Ciclo de vida do pedido
Transições permitidas:

| De | Para |
|---|---|
| CRIADO | PAGO, CANCELADO |
| PAGO | EM_TRANSPORTE, CANCELADO |
| EM_TRANSPORTE | ENTREGUE |
| ENTREGUE | — (estado final) |
| CANCELADO | — (estado final) |

Qualquer outra transição deve ser **rejeitada** com HTTP **422 Unprocessable Entity** e mensagem clara.
Ex.: um pedido CANCELADO jamais pode ir para EM_TRANSPORTE — isso já gerou caminhão saindo com carga de pedido cancelado.

## RN-03 — Recurso inexistente
Buscar ou alterar pedido/cliente que não existe retorna **404**, nunca 500.

## RN-04 — Padrão de erro da API
Todo erro retorna JSON no formato:
```json
{
  "timestamp": "2026-11-23T10:15:30",
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Pedido 99 não encontrado",
  "path": "/api/pedidos/99"
}
```
Erros de validação (400) devem listar os campos inválidos.
