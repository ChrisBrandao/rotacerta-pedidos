package com.rotacerta.pedidos.domain;

/**
 * Ciclo de vida de um pedido.
 * <p>
 * Fluxo esperado pelo negocio (ver docs/REGRAS_DE_NEGOCIO.md):
 * CRIADO -> PAGO -> EM_TRANSPORTE -> ENTREGUE
 * CRIADO ou PAGO -> CANCELADO
 */
public enum StatusPedido {
    CRIADO,
    PAGO,
    EM_TRANSPORTE,
    ENTREGUE,
    CANCELADO;

    public boolean podeTransicionarPara(StatusPedido novo) {
        return switch (this) {
            case CRIADO -> novo == PAGO || novo == CANCELADO;
            case PAGO -> novo == EM_TRANSPORTE || novo == CANCELADO;
            case EM_TRANSPORTE -> novo == ENTREGUE;
            case ENTREGUE, CANCELADO -> false;

        };
    }

}