package br.com.covidbenchmarkapi.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "dados_comparados",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"data", "primeiro_estado", "segundo_estado"})})
@NoArgsConstructor
public class DadosComparados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Column(name = "primeiro_estado", length = 2)
    private String primeiroEstado;

    @Setter
    @Column(name = "segundo_estado", length = 2)
    private String segundoEstado;

    @Setter
    @Column(length = 10)
    private String data;

    @Setter
    @Column(name = "diferenca_confirmacoes")
    private long diferencaConfirmacoes;

    @Setter
    @Column(name = "diferenca_mortes")
    private long diferencaMortes;

    @Setter
    @Column(name = "diferenca_confirmados_sobre_cem_mil_habitantes")
    private BigDecimal diferencaConfirmadosSobreCemMilHabitantes;

    @Setter
    @Column(name = "diferenca_mortes_sobre_confirmados")
    private BigDecimal diferencaMortesSobreConfirmados;

    public DadosComparados(String primeiroEstado, String segundoEstado, String data) {
        this.primeiroEstado = primeiroEstado;
        this.segundoEstado = segundoEstado;
        this.data = data;
    }
}