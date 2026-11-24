package com.rotacerta.pedidos.exception;

public class RecursoNaoEncontradoException extends RuntimeException{
    public RecursoNaoEncontradoException (String mensagem){
        super(mensagem);
    }
}
