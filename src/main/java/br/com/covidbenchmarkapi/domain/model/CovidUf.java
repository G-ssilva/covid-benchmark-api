package br.com.covidbenchmarkapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "covid_uf",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"data", "uf"})})
public class CovidUf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @NotNull
    @Column(length = 10)
    private String data;

    @Setter
    @NotNull
    @Column(length = 2)
    private String uf;

    @Setter
    @NotNull
    private Integer confirmacoes;

    @Setter
    @NotNull
    private Integer mortes;

    @Setter
    @NotNull
    @Column(name = "confirmados_sobre_cem_mil_habitantes")
    private BigDecimal confirmadosSobreCemMilHabitantes;

    @Setter
    @NotNull
    @Column(name = "mortes_sobre_confirmados")
    private BigDecimal mortesSobreConfirmados;

    @Setter
    @NotNull
    @Column(name = "possui_dados")
    private boolean possuiDados;
}