package br.com.covidbenchmarkapi.domain.repository;

import br.com.covidbenchmarkapi.domain.model.CovidUf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CovidUfRepository extends JpaRepository<CovidUf, Long> {
    Optional<CovidUf> findByUfAndData(String UF, String data);
}