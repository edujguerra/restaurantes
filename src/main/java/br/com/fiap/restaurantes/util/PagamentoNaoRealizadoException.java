package br.com.fiap.restaurantes.util;

public class PagamentoNaoRealizadoException extends RuntimeException{

    public PagamentoNaoRealizadoException(String mensagem) {
        super(mensagem);
    }
}
