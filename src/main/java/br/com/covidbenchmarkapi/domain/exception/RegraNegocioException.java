package br.com.covidbenchmarkapi.domain.exception;

public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(String message) {
        super(message);
    }
}