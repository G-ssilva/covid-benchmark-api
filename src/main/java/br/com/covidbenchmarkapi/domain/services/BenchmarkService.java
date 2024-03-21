package br.com.covidbenchmarkapi.domain.services;

import br.com.covidbenchmarkapi.domain.model.Benchmark;
import br.com.covidbenchmarkapi.domain.repository.BenchmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenchmarkService {

    @Autowired
    private BenchmarkRepository repository;

    public void persistir(Benchmark benchmark) {
        repository.save(benchmark);
    }
}