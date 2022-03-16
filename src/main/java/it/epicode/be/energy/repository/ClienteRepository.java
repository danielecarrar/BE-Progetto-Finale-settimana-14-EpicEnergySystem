package it.epicode.be.energy.repository;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.be.energy.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public Page<Cliente> findAllBySedeLegaleComuneProvinciaSigla(String sigla, Pageable pageable); 

	public Page<Cliente> findAllByDataInserimento(Date dataInserimento, Pageable pageable);

	public Page<Cliente> findAllByDataUltimoContatto(Date dataUltimoContatto, Pageable pageable);

	public Page<Cliente> findAll(Pageable pageable);

	public Page<Cliente> findAllSortedByFatturatoAnnuale(BigDecimal fatturato, Pageable pageable);
	
	@Query("Select c from Cliente c Order by c.sedeLegale.comune.provincia")
	public Page<Cliente> findAllByProvincia(Pageable pageable); 
	
	@Query("Select c from Cliente c where c.ragioneSociale like '%:s%' ")
	public Page<Cliente> findByParteRagioneSociale(String s, Pageable pageable);
	
	public Page<Cliente> findByOrderByNomeContattoAsc(Pageable pageable);
	
	//not implemented
	
	public Page<Cliente> findByDataInserimento(int g, int m, int a, Pageable pageable);

	public Page<Cliente> findByDataUltimoContatto(int g, int m, int a, Pageable pageable);
}
