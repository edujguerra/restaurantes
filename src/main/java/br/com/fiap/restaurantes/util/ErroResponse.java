package br.com.fiap.restaurantes.util;

public class ErroResponse {
    private final String mensagem;
    private final int status;

    public ErroResponse(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getStatus() {
        return status;
    }
}
