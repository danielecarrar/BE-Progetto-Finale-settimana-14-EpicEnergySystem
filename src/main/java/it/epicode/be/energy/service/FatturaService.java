package it.epicode.be.energy.service;

import java.math.BigDecimal;
import java.util.List;
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
	FatturaRepository fatturaRepository;

	public Page<Fattura> findByStato(String statoFattura, Pageable pageable) {
		try {
			Page<Fattura> fatture = fatturaRepository.findAllByStato(statoFattura, pageable);
			if (fatture.hasContent()) {
				return fatture;
			} else
				return null;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Fattura> findFatturaByClienteId(Long IDCliente, Pageable pageable) {
		try {
			return fatturaRepository.findFatturaByIdCliente(IDCliente, pageable);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Fattura> findByRange(BigDecimal min, BigDecimal max, Pageable pageable) {
		try {
			return fatturaRepository.findByRange(min, max, pageable);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Fattura save(Fattura fattura) {
		return fatturaRepository.save(fattura);
	}

	public void delete(Long id) {
		Optional<Fattura> fatturaDaEliminare = fatturaRepository.findById(id);
		if (fatturaDaEliminare.isPresent()) {
			fatturaDaEliminare.get().setCliente(null);
			fatturaRepository.deleteById(id);
		} else {
			throw new EnergySystemException("Nessuna fattura con id " + id + " presente!");
		}
	}

	public Fattura update(Long id, Fattura fattura) {
		Optional<Fattura> fatturaDB = fatturaRepository.findById(id);
		if (fatturaDB.isPresent()) {
			Fattura fatturaDaAggiornare = fatturaDB.get();
			fatturaDaAggiornare.setNumeroFattura(fattura.getNumeroFattura());
			fatturaDaAggiornare.setAnno(fattura.getAnno());
			fatturaDaAggiornare.setData(fattura.getData());
			fatturaDaAggiornare.setImporto(fattura.getImporto());
			fatturaDaAggiornare.setStato(fattura.getStato());
			fatturaDaAggiornare.setCliente(fattura.getCliente());
			return fatturaRepository.save(fatturaDaAggiornare);
		} else
			throw new EnergySystemException("Impossibile aggiornare la fattura! Controlla che l'id sia corretto!");
	}

	public Page<Fattura> findAll(Pageable pageable) {
		return fatturaRepository.findAll(pageable);
	}

	public List<Fattura> findAll() {
		return fatturaRepository.findAll();
	}
}