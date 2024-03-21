package br.com.covidbenchmarkapi.domain.dto;

import br.com.covidbenchmarkapi.domain.model.Benchmark;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BenchmarkDto {
    @NotBlank
    private String nomeBenchmark;

    private String observacao;
    private long id;
    private String primeiroEstado;
    private String segundoEstado;
    private String data;

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

    public BenchmarkDto(Benchmark benchmark){
        this.nomeBenchmark = benchmark.getNomeBenchmark();
        this.observacao = benchmark.getObservacao();
        this.id = benchmark.getId();
        this.primeiroEstado = benchmark.getPrimeiroEstado();
        this.segundoEstado = benchmark.getSegundoEstado();
        this.data = benchmark.getData();
    }
}