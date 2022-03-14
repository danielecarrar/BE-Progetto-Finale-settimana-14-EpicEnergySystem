package it.epicode.be.energy.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exceptions.EnergySystemException;
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.repository.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepo;

	public Page<Fattura> findByStato(String statoFattura, Pageable pageable) {
		try {
			Page<Fattura> fatture = fatturaRepo.findAllByStato(statoFattura, pageable);
			if (fatture.hasContent()) {
				return fatture;
			} else
				return null;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Fattura> findFatturaByClienteId(Long idCliente, Pageable pageable) {
		try {
			return fatturaRepo.findFatturaByIdCliente(idCliente, pageable);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	
	public Page<Fattura> findByRange(BigDecimal min, BigDecimal max, Pageable pageable) {
		try {
			return fatturaRepo.findByRange(min, max, pageable);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Fattura save(Fattura fattura) {
		return fatturaRepo.save(fattura);
	}

	public void delete(Long id) {
		Optional<Fattura> result = fatturaRepo.findById(id);
		if (result.isPresent()) {
			result.get().setCliente(null);
			fatturaRepo.deleteById(id);
		} else {
			throw new EnergySystemException("Nessun Risultato!");
		}
	}
	
	public Fattura update(Long id, Fattura fattura) {
		Optional<Fattura> fatturaResult = fatturaRepo.findById(id);
		if (fatturaResult.isPresent()) {
			Fattura update = fatturaResult.get();
			update.setNumeroFattura(fattura.getNumeroFattura());
			update.setAnno(fattura.getAnno());
			update.setData(fattura.getData());
			update.setImporto(fattura.getImporto());
			update.setStato(fattura.getStato());
			update.setCliente(fattura.getCliente());
			return fatturaRepo.save(update);
		}
		else 
			throw new EnergySystemException("Impossibile aggiornare la fattura!");
	}
	
	public Page<Fattura> findAll(Pageable pageable) {
		return fatturaRepo.findAll(pageable);
	}
}