package br.com.covidbenchmarkapi.domain.services;

import br.com.covidbenchmarkapi.domain.dto.CovidUfDto;
import br.com.covidbenchmarkapi.fontedados.ConsultaFonteDados;
import br.com.covidbenchmarkapi.domain.model.CovidUf;
import br.com.covidbenchmarkapi.domain.repository.CovidUfRepository;
import br.com.covidbenchmarkapi.fontedados.dto.CovidUfFonteDadosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CovidUfService {

    @Autowired
    private ConsultaFonteDados consultaFonteDados;

    @Autowired
    private CovidUfRepository repository;

    public List<CovidUf> montarInformacoes(String primeiroEstado, String segundoEstado, String data)
            throws IOException, InterruptedException {
        List<CovidUf> covidUfList = new ArrayList<>();
        covidUfList.add(listarPorEstadoData(primeiroEstado, data));
        covidUfList.add(listarPorEstadoData(segundoEstado, data));

        return covidUfList;
    }

    public CovidUf listarPorEstadoData(String estado, String data) throws IOException, InterruptedException {
        Optional<CovidUf> covidUf = repository.findByUfAndData(estado, data);

        if (covidUf.isEmpty()) {
            return buscarInformacoes(estado, data);
        }

        return covidUf.get();
    }

    public void persistir(CovidUf covidUf) {
        repository.save(covidUf);
    }


    public CovidUf buscarInformacoes(String estado, String data) throws IOException, InterruptedException {
        CovidUf covidUf = consultaFonteDados.consultar(estado, data);

        if (covidUf == null) {
            CovidUf covidUfSemDados = estadoDataSemDados(estado, data);
            persistir(covidUfSemDados);

            return covidUfSemDados;
        }

        persistir(covidUf);

        return covidUf;
    }

    private CovidUf estadoDataSemDados(String estado, String data) {
        CovidUfFonteDadosDto covidUfSemDados = new CovidUfFonteDadosDto();
        return covidUfSemDados.criarEntidadeSemDados(estado, data);
    }

    public List<CovidUfDto> montarRetorno(List<CovidUf> covidUfList) {
        List<CovidUfDto> covidUfDtoList = new ArrayList<>();
        covidUfList.forEach(covidUf -> covidUfDtoList.add(new CovidUfDto(covidUf)));

        return covidUfDtoList;
    }
}