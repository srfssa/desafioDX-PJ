package br.com.duxusdesafio.util.exception;

public class DefaultException extends RuntimeException {

    private int status;

    public DefaultException(int status, String mensagem) {
        super(mensagem);
        this.status = status;
    }

    public int getStatus() { return status; }

    public String getMensagem() { return super.getMessage(); }
}
