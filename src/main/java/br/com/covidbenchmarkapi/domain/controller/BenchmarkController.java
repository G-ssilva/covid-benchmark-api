package br.com.covidbenchmarkapi.domain.controller;

import br.com.covidbenchmarkapi.domain.dto.BenchmarkDto;
import br.com.covidbenchmarkapi.domain.dto.CovidUfDto;
import br.com.covidbenchmarkapi.domain.model.Benchmark;
import br.com.covidbenchmarkapi.domain.model.CovidUf;
import br.com.covidbenchmarkapi.domain.services.BenchmarkService;
import br.com.covidbenchmarkapi.domain.services.CovidUfService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/benchmark")
public class BenchmarkController {
    @Autowired
    private CovidUfService covidUfService;

    @Autowired
    private BenchmarkService benchmarkService;

    @PostMapping("/{primeiroEstado}/{segundoEstado}/{data}")
    public List<CovidUfDto> benchmark(@Valid @NotBlank @PathVariable String primeiroEstado,
                                      @Valid @NotBlank @PathVariable String segundoEstado,
                                      @Valid @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}") @PathVariable String data)
            throws IOException, InterruptedException {
        List<CovidUf> covidUfList = covidUfService.montarInformacoes(primeiroEstado, segundoEstado, data);

        return covidUfService.montarRetorno(covidUfList);
    }

    @PostMapping("/{primeiroEstado}/{segundoEstado}/{data}/salvar")
    public void salvarBenchmark(@Valid @NotBlank @PathVariable String primeiroEstado,
                                @Valid @NotBlank @PathVariable String segundoEstado,
                                @Valid @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}") @PathVariable String data,
                                @Valid @RequestBody BenchmarkDto dados) {
        Benchmark benchmark = dados.criarEntidade(primeiroEstado, segundoEstado, data);
        benchmarkService.persistir(benchmark);
    }

    @GetMapping("/listar")
    public List<BenchmarkDto> listarTodos() {
        return benchmarkService.retornarBenchmarksSalvos();
    }

    @GetMapping("/listar/{id}")
    public BenchmarkDto listarPorId(@Valid @NotNull @PathVariable long id) {
        return benchmarkService.listarPorId(id);
    }
}