package br.com.covidbenchmarkapi.domain.dto;

import br.com.covidbenchmarkapi.domain.model.CovidUf;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidUfDto {
    private String data;
    private String uf;
    private int confirmacoes;
    private int mortes;
    private float confirmadosSobreCemMilHabitantes;
    private float mortesSobreConfirmados;
    private boolean possuiDados;

    public CovidUfDto(CovidUf covidUf) {
        this.data = covidUf.getData();
        this.uf = covidUf.getUf();
        this.confirmacoes = covidUf.getConfirmacoes();
        this.mortes = covidUf.getMortes();
        this.confirmadosSobreCemMilHabitantes = covidUf.getConfirmadosSobreCemMilHabitantes().floatValue();
        this.mortesSobreConfirmados = covidUf.getMortesSobreConfirmados().floatValue();
        this.possuiDados = covidUf.isPossuiDados();
    }

}