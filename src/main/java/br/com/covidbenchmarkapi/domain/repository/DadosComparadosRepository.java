package br.com.covidbenchmarkapi.domain.repository;

import br.com.covidbenchmarkapi.domain.model.DadosComparados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DadosComparadosRepository extends JpaRepository<DadosComparados, Long> {
    Optional<DadosComparados> findByPrimeiroEstadoAndSegundoEstadoAndData(String primeiroEstado, String segundoEstado,
                                                                          String data);
}