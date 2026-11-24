package com.rotacerta.pedidos.exception;

import com.rotacerta.pedidos.domain.StatusPedido;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransicaoInvalidaException extends RuntimeException {

    public TransicaoInvalidaException(StatusPedido atual, StatusPedido novo) {
        super("Transição de " + atual + " para " + novo + " não permitida");
    }

}
