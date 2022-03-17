package it.epicode.be.energy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exceptions.EnergySystemException;
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepository;

	public Page<Indirizzo> findAll(Pageable pageable) {
		return indirizzoRepository.findAll(pageable);
	}

	public Optional<Indirizzo> findById(Long id) {
		return indirizzoRepository.findById(id);
	}

	public Indirizzo save(Indirizzo indirizzo) {
		Indirizzo ind = new Indirizzo();
		ind.setVia(indirizzo.getVia());
		ind.setCivico(indirizzo.getCivico());
		ind.setCap(indirizzo.getCap());
		ind.setComune(indirizzo.getComune());
		ind.setLocalita(indirizzo.getLocalita());
		return indirizzoRepository.save(ind);
	}

	public void delete(Long id) {
		if (indirizzoRepository.findById(id).isPresent()) {
			Indirizzo ind = indirizzoRepository.findById(id).get();
			ind.setComune(null);
			indirizzoRepository.delete(ind);
		}
	}

	public Indirizzo update(Long id, Indirizzo indirizzo) {
		Optional<Indirizzo> indirizzoTrovato = indirizzoRepository.findById(id);
		if (indirizzoTrovato.isPresent()) {

			Indirizzo indirizzoDaAggiornare = indirizzoTrovato.get();

			indirizzoDaAggiornare.setCap(indirizzo.getCap());
			indirizzoDaAggiornare.setCivico(indirizzo.getCivico());
			indirizzoDaAggiornare.setComune(indirizzo.getComune());
			indirizzoDaAggiornare.setVia(indirizzo.getVia());
			indirizzoDaAggiornare.setLocalita(indirizzo.getLocalita());

			return indirizzoDaAggiornare;

		} else {
			throw new EnergySystemException("Indirizzo non presente!");
		}
	}
}
