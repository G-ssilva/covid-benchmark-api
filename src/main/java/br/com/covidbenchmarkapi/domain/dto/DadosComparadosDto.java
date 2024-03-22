package br.com.covidbenchmarkapi.domain.dto;

import br.com.covidbenchmarkapi.domain.model.DadosComparados;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosComparadosDto {
    private String primeiroEstado;
    private String segundoEstado;
    private String data;
    private long diferencaConfirmacoes;
    private long diferencaMortes;
    private float diferencaConfirmadosSobreCemMilHabitantes;
    private float diferencaMortesSobreConfirmados;

    public DadosComparadosDto(DadosComparados dadosComparados) {
        this.primeiroEstado = dadosComparados.getPrimeiroEstado();
        this.segundoEstado = dadosComparados.getSegundoEstado();
        this.data = dadosComparados.getData();
        this.diferencaConfirmacoes = dadosComparados.getDiferencaConfirmacoes();
        this.diferencaMortes = dadosComparados.getDiferencaMortes();
        this.diferencaConfirmadosSobreCemMilHabitantes = dadosComparados
                .getDiferencaConfirmadosSobreCemMilHabitantes().floatValue();
        this.diferencaMortesSobreConfirmados = dadosComparados
                .getDiferencaMortesSobreConfirmados().floatValue();
    }
}