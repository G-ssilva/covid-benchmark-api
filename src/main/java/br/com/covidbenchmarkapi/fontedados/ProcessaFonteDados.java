package br.com.covidbenchmarkapi.fontedados;

import br.com.covidbenchmarkapi.exception.RegraNegocioException;
import br.com.covidbenchmarkapi.domain.model.CovidUf;
import br.com.covidbenchmarkapi.fontedados.dto.CovidUfFonteDadosDto;
import br.com.covidbenchmarkapi.fontedados.dto.RetornoFonteDadosDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.List;

@Component
@Log
public class ProcessaFonteDados {

    public CovidUf processar(HttpResponse<String> response) throws JsonProcessingException {
        RetornoFonteDadosDto retornoFonteDadosDto = RetornoFonteDadosDto.mapear(response.body());
        List<CovidUfFonteDadosDto> results = retornoFonteDadosDto.getResults();

        log.info(response.body());

        if (results.isEmpty()) {
            return null;
        }

        if (results.size() > 1) {
            throw new RegraNegocioException("API externa devolveu mais de um resultado");
        }

        return results.get(0).criarEntidade();
    }
}