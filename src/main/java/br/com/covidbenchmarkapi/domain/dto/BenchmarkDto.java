package br.com.covidbenchmarkapi.domain.dto;

import br.com.covidbenchmarkapi.domain.model.Benchmark;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BenchmarkDto {
    @NotBlank
    private String nomeBenchmark;

    private String observacao;

    public Benchmark criarEntidade(String primeiroEstado, String segundoEstado, String data) {
        Benchmark benchmark = new Benchmark();

        benchmark.setPrimeiroEstado(primeiroEstado);
        benchmark.setSegundoEstado(segundoEstado);
        benchmark.setData(data);
        benchmark.setNomeBenchmark(nomeBenchmark);

        if (StringUtils.isNotBlank(observacao)) {
            benchmark.setObservacao(observacao);
        }

        return benchmark;
    }
}