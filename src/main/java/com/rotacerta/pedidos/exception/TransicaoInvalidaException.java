package com.rotacerta.pedidos.exception;

import com.rotacerta.pedidos.domain.StatusPedido;

public class TransicaoInvalidaException extends RuntimeException {

    public TransicaoInvalidaException(StatusPedido atual, StatusPedido novo) {
        super("Transição de " + atual + " para " + novo + " não permitida");
    }

}
