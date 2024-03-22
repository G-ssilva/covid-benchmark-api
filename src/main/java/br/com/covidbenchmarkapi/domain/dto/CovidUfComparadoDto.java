package br.com.covidbenchmarkapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CovidUfComparadoDto {
    private List<CovidUfDto> covidUfDtoList;
    private DadosComparadosDto dadosComparadosDto;

    public CovidUfComparadoDto(List<CovidUfDto> covidUfDtos, DadosComparadosDto dadosComparadosDto) {
        this.covidUfDtoList = covidUfDtos;
        this.dadosComparadosDto = dadosComparadosDto;
    }
}