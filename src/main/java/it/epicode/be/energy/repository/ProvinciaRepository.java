package it.epicode.be.energy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.energy.model.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
	Optional<Provincia> findBySigla(String sigla);
	public Page<Provincia> findByRegione(String regione, Pageable pageable);
	Optional<Provincia> findByNome(String string);
}
