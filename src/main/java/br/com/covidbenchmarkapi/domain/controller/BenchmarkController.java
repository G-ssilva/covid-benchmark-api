package br.com.covidbenchmarkapi.domain.controller;

import br.com.covidbenchmarkapi.domain.dto.CovidUfDto;
import br.com.covidbenchmarkapi.domain.model.CovidUf;
import br.com.covidbenchmarkapi.domain.services.CovidUfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/benchmark")
public class BenchmarkController {
    @Autowired
    private CovidUfService covidUfService;

    @PostMapping("/{primeiroEstado}/{segundoEstado}/{data}")
    public List<CovidUfDto> benchmark(@PathVariable String primeiroEstado, @PathVariable String segundoEstado,
                                      @PathVariable String data) throws IOException, InterruptedException {
        List<CovidUf> covidUfList = covidUfService.montarInformacoes(primeiroEstado, segundoEstado, data);

        return covidUfService.montarRetorno(covidUfList);
    }
}