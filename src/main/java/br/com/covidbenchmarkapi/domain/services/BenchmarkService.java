package br.com.covidbenchmarkapi.domain.services;

import br.com.covidbenchmarkapi.domain.dto.BenchmarkDto;
import br.com.covidbenchmarkapi.domain.model.Benchmark;
import br.com.covidbenchmarkapi.domain.repository.BenchmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BenchmarkService {

    @Autowired
    private BenchmarkRepository repository;

    public void persistir(Benchmark benchmark) {
        repository.save(benchmark);
    }

    public List<BenchmarkDto> retornarBenchmarksSalvos() {
        List<Benchmark> benchmarks = listarTodos();
        List<BenchmarkDto> benchmarkDtos = new ArrayList<>();

        benchmarks.forEach(benchmark -> benchmarkDtos.add(new BenchmarkDto(benchmark)));

        return benchmarkDtos;
    }

    private List<Benchmark> listarTodos() {
        return repository.findAll();
    }
}