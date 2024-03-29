package br.com.covidbenchmarkapi.domain.repository;

import br.com.covidbenchmarkapi.domain.model.Benchmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenchmarkRepository extends JpaRepository<Benchmark, Long> {
    List<Benchmark> findByNomeBenchmarkContainingIgnoreCase(String nomeBenchmark);
}