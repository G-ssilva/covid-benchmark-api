package br.com.covidbenchmarkapi.fontedados;

import br.com.covidbenchmarkapi.domain.model.CovidUf;
import br.com.covidbenchmarkapi.domain.services.ConsultaFonteDadosService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@NoArgsConstructor
@Component
public class ConsultaFonteDados {
    @Autowired
    private HttpClient client;

    @Autowired
    private ProcessaFonteDados processaFonteDados;

    @Autowired
    private ConsultaFonteDadosService consultaFonteDadosService;

    public ConsultaFonteDados(HttpClient client, ProcessaFonteDados processaFonteDados) {
        this.client = client;
        this.processaFonteDados = processaFonteDados;
    }

    public CovidUf consultar(String estado, String data) throws IOException, InterruptedException {
        String url = consultaFonteDadosService.montaUrlComFiltroDataEstado(estado, data);
        HttpRequest request = consultaFonteDadosService.montaRequisicao(url);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (!consultaFonteDadosService.isValidResponse(response)) {
            throw new RuntimeException("Não foi possível obter os dados para o Estado e a data definida. " +
                    "Aguarde alguns minutos para tentar novamente. " +
                    "Caso o problema persista, contate o administrador.");
        }

        return processaFonteDados.processar(response);
    }
}