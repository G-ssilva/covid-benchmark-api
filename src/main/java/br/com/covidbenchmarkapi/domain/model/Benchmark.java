package br.com.covidbenchmarkapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Benchmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @NotNull
    @Column(name = "primeiro_estado", length = 2)
    private String primeiroEstado;

    @Setter
    @NotNull
    @Column(name = "segundo_estado", length = 2)
    private String segundoEstado;

    @Setter
    @NotNull
    @Column(length = 10)
    private String data;

    @Setter
    @NotNull
    @Column(name = "nome_benchmark")
    private String nomeBenchmark;

    @Setter
    private String observacao;
}