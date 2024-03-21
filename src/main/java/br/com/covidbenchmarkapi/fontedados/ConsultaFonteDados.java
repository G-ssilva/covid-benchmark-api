package br.com.covidbenchmarkapi.fontedados;

import br.com.covidbenchmarkapi.domain.model.CovidUf;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@NoArgsConstructor
@Component
public class ConsultaFonteDados {
    @Autowired
    private HttpClient client;

    @Autowired
    private ProcessaFonteDados processaFonteDados;

    @Value("${api.externa.token}")
    private String API_TOKEN;

    @Value("${api.externa.url.base}")
    private String API_URL_BASE;

    private final String API_URL_FILTRO_TIPO_LOCAL = "?place_type=state";
    private final String API_URL_FILTRO_ESTADO = "&state=";
    private final String API_URL_FILTRO_DATA = "&date=";

    public ConsultaFonteDados(HttpClient client, ProcessaFonteDados processaFonteDados) {
        this.client = client;
        this.processaFonteDados = processaFonteDados;
    }

    public CovidUf consultar(String estado, String data) throws IOException, InterruptedException {
        if (API_TOKEN == null || API_URL_BASE == null) {
            throw new RuntimeException("Não foi possível definir o valor do token e/ou da URL base da API Externa");
        }

        String url = montaUrlComFiltroDataEstado(estado, data);
        HttpRequest request = montaRequisicao(url);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response == null) {
            throw new RuntimeException("Não foi possível obter o response da API externa");
        }

        return processaFonteDados.processar(response);
    }

    private HttpRequest montaRequisicao(String url) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Authorization", "Token " + API_TOKEN)
                .timeout(Duration.ofSeconds(10))
                .build();
    }

    private String montaUrlComFiltroDataEstado(String estado, String data) {
        return API_URL_BASE + API_URL_FILTRO_TIPO_LOCAL + API_URL_FILTRO_ESTADO + estado
                + API_URL_FILTRO_DATA + data;
    }
}