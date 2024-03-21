package br.com.covidbenchmarkapi.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class ConsultaFonteDadosService {

    @Value("${api.externa.token}")
    private String API_TOKEN;

    @Value("${api.externa.url.base}")
    private String API_URL_BASE;

    private final String API_URL_FILTRO_TIPO_LOCAL = "?place_type=state";
    private final String API_URL_FILTRO_ESTADO = "&state=";
    private final String API_URL_FILTRO_DATA = "&date=";

    public HttpRequest montaRequisicao(String url) {
        if (API_TOKEN == null) {
            throw new RuntimeException("Não foi possível definir o valor do token da API Externa");
        }

        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Authorization", "Token " + API_TOKEN)
                .timeout(Duration.ofSeconds(10))
                .build();
    }

    public String montaUrlComFiltroDataEstado(String estado, String data) {
        if (API_URL_BASE == null) {
            throw new RuntimeException("Não foi possível definir o valor da URL base da API Externa");
        }

        return API_URL_BASE + API_URL_FILTRO_TIPO_LOCAL + API_URL_FILTRO_ESTADO + estado
                + API_URL_FILTRO_DATA + data;
    }

    public boolean isValidResponse(HttpResponse<String> response) {
        return response != null && response.statusCode() == 200;
    }
}