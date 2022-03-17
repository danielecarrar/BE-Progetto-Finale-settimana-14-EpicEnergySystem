package it.epicode.be.energy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.energy.model.Province;

public interface ProvinciaRepository extends JpaRepository<Province, Long> {

	Optional<Province> findBySigla(String sigla);

	public Page<Province> findByRegione(String regione, Pageable pageable);

	Optional<Province> findByNome(String string);
}
