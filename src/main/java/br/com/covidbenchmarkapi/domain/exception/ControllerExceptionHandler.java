package br.com.covidbenchmarkapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private RestErrorDto regraNegocioHandler(RegraNegocioException e) {
        return new RestErrorDto(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private RestErrorDto runtimeErrorHandler(RuntimeException e) {
        return new RestErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private RestErrorDto unexpectedExceptionHandler(Exception e) {
        return new RestErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, "Ops! Ocorreu um erro inesperado");
    }
}