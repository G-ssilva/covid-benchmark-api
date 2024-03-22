package br.com.covidbenchmarkapi.domain.services;

import br.com.covidbenchmarkapi.domain.dto.DadosComparadosDto;
import br.com.covidbenchmarkapi.domain.model.CovidUf;
import br.com.covidbenchmarkapi.domain.model.DadosComparados;
import br.com.covidbenchmarkapi.domain.repository.DadosComparadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DadosComparadosService {

    @Autowired
    private DadosComparadosRepository repository;

    @Autowired
    private CovidUfService covidUfService;

    public DadosComparados montarInformacoes(String primeiroEstado, String segundoEstado, String data) throws IOException, InterruptedException {

        Optional<DadosComparados> dadosComparados = existeNoBanco(primeiroEstado, segundoEstado, data);

        if (dadosComparados.isEmpty()) {
            return compararDados(primeiroEstado, segundoEstado, data);
        }

        return dadosComparados.get();
    }

    private Optional<DadosComparados> existeNoBanco(String primeiroEstado, String segundoEstado, String data) {
        return repository.findByPrimeiroEstadoAndSegundoEstadoAndData(primeiroEstado,
                segundoEstado, data);
    }

    private DadosComparados compararDados(String primeiroEstado, String segundoEstado, String data) throws IOException, InterruptedException {
        CovidUf primeiroEstadoComparar = covidUfService.listarPorEstadoData(primeiroEstado, data);
        CovidUf segundoEstadoComparar = covidUfService.listarPorEstadoData(segundoEstado, data);

        DadosComparados dadosComparados = new DadosComparados(primeiroEstado, segundoEstado, data);

        long diferencaConfirmacoes = primeiroEstadoComparar.getConfirmacoes() - segundoEstadoComparar.getConfirmacoes();
        long diferencaMortes = primeiroEstadoComparar.getMortes() - segundoEstadoComparar.getMortes();
        BigDecimal diferencaConfirmadosSobreCemMilHabitantes = primeiroEstadoComparar
                .getConfirmadosSobreCemMilHabitantes().subtract(segundoEstadoComparar.getConfirmadosSobreCemMilHabitantes());

        BigDecimal diferencaMortesSobreConfirmados = primeiroEstadoComparar
                .getMortesSobreConfirmados().subtract(segundoEstadoComparar.getMortesSobreConfirmados());

        dadosComparados.setDiferencaConfirmacoes(diferencaConfirmacoes);
        dadosComparados.setDiferencaMortes(diferencaMortes);
        dadosComparados.setDiferencaConfirmadosSobreCemMilHabitantes(diferencaConfirmadosSobreCemMilHabitantes);
        dadosComparados.setDiferencaMortesSobreConfirmados(diferencaMortesSobreConfirmados);

        return dadosComparados;
    }

    public DadosComparadosDto montarRetorno(DadosComparados dadosComparados) {
        return new DadosComparadosDto(dadosComparados);
    }

    public DadosComparados salvarInformacoes(String primeiroEstado, String segundoEstado, String data) throws IOException, InterruptedException {
        Optional<DadosComparados> dadosComparados = existeNoBanco(primeiroEstado, segundoEstado, data);

        if (dadosComparados.isEmpty()) {
            DadosComparados dadosCOmparadosNew = compararDados(primeiroEstado, segundoEstado, data);
            persistir(dadosCOmparadosNew);

            return dadosCOmparadosNew;
        }

        return dadosComparados.get();
    }

    private void persistir(DadosComparados dadosComparadosNew) {
        repository.save(dadosComparadosNew);
    }
}