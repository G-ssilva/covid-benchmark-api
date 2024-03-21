package br.com.covidbenchmarkapi.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.Collections.emptyList;

@Getter
@Setter
@AllArgsConstructor
public class RestErrorDto {
    private HttpStatus status;
    private String message;
    private List<String> errors = emptyList();

    public RestErrorDto(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}