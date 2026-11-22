package com.rotacerta.pedidos.domain;

/**
 * Ciclo de vida de um pedido.
 *
 * Fluxo esperado pelo negocio (ver docs/REGRAS_DE_NEGOCIO.md):
 * CRIADO -> PAGO -> EM_TRANSPORTE -> ENTREGUE
 * CRIADO ou PAGO -> CANCELADO
 */
public enum StatusPedido {
    CRIADO,
    PAGO,
    EM_TRANSPORTE,
    ENTREGUE,
    CANCELADO
}
