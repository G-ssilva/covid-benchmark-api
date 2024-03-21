package br.com.covidbenchmarkapi.fontedados.dto;

import br.com.covidbenchmarkapi.domain.model.CovidUf;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CovidUfFonteDadosDto {
    @JsonProperty("date")
    private String data;

    @JsonProperty("state")
    private String uf;

    @JsonProperty("confirmed")
    private int confirmacoes;

    @JsonProperty("deaths")
    private int mortes;

    @JsonProperty("confirmed_per_100k_inhabitants")
    private float confirmadosSobreCemMilHabitantes;

    @JsonProperty("death_rate")
    private float mortesSobreConfirmados;

    public CovidUf criarEntidade() {
        CovidUf covidUf = new CovidUf();

        try {
            covidUf.setData(data);
            covidUf.setUf(uf.toUpperCase());
            covidUf.setConfirmacoes(confirmacoes);
            covidUf.setMortes(mortes);
            covidUf.setConfirmadosSobreCemMilHabitantes(new BigDecimal(confirmadosSobreCemMilHabitantes));
            covidUf.setMortesSobreConfirmados(new BigDecimal(mortesSobreConfirmados));
            covidUf.setPossuiDados(true);
        } catch (NullPointerException npe) {
            //TODO implementar exceptions personalizadas
            throw new RuntimeException("Não foi possível definir um ou mais campos");
        }

        return covidUf;
    }

    public CovidUf criarEntidadeSemDados(String estado, String data) {
        CovidUf covidUf = new CovidUf();

        covidUf.setData(data);
        covidUf.setUf(estado);
        covidUf.setConfirmacoes(0);
        covidUf.setMortes(0);
        covidUf.setConfirmadosSobreCemMilHabitantes(new BigDecimal(0));
        covidUf.setMortesSobreConfirmados(new BigDecimal(0));
        covidUf.setPossuiDados(false);

        return covidUf;
    }
}