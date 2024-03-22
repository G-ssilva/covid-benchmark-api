package br.com.covidbenchmarkapi.fontedados;

import br.com.covidbenchmarkapi.exception.RegraNegocioException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProcessaFonteDadosTest {

    private static ProcessaFonteDados processaFonteDados;

    @BeforeAll
    static void setUp(){
        processaFonteDados = new ProcessaFonteDados();
    }

    @Test
    @DisplayName("Deve retornar exception de Regra de Negócio quando a API Externa devolver mais de um resultado na consulta")
    void deveLancarExceptionRetornoApiExternaMaisUmResultado() {
        HttpResponse<String> response = mock(HttpResponse.class);

        String responseDoisEstados = criarResponseComDoisResultados();

        when(response.body()).thenReturn(responseDoisEstados);

        RegraNegocioException exception = Assertions.assertThrows(RegraNegocioException.class, () ->
                processaFonteDados.processar(response)
        );

        Assertions.assertEquals("API externa devolveu mais de um resultado", exception.getMessage());
    }

    private String criarResponseComDoisResultados() {
        return "{\"count\":1,\"next\":null,\"previous\":null,\"results\":[{\"city\":null,\"city_ibge_code\":\"52\"," +
                "\"confirmed\":0,\"confirmed_per_100k_inhabitants\":0,\"date\":\"2021-01-06\",\"death_rate\":0," +
                "\"deaths\":0,\"estimated_population\":0,\"estimated_population_2019\":0,\"is_last\":false," +
                "\"order_for_place\":298,\"place_type\":\"state\",\"state\":\"GO\"},{\"city\":null," +
                "\"city_ibge_code\":\"52\",\"confirmed\":0,\"confirmed_per_100k_inhabitants\":0,\"date\":\"2021-01-06\"," +
                "\"death_rate\":0,\"deaths\":0,\"estimated_population\":0,\"estimated_population_2019\":0," +
                "\"is_last\":false,\"order_for_place\":298,\"place_type\":\"state\",\"state\":\"GO\"}]}\n";
    }
}