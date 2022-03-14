package it.epicode.be.energy.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.be.energy.model.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {

	public Page<Fattura> findAll(Pageable pageable);
	
	@Query("Select f from Fattura f where f.stato= :stato")
	public Page<Fattura> findAllByStato(String stato, Pageable pageable);

	@Query("Select f from Fattura f where f.cliente.id=:id")
	public Page<Fattura> findFatturaByIdCliente(Long id, Pageable pageable);

	//PER RICERCA RANGE DI IMPORTO IN FATTURA
	@Query("Select f from Fattura f where f.importo between :min and :max")
	public Page<Fattura> findByRange(BigDecimal min, BigDecimal max, Pageable pageable);

}
