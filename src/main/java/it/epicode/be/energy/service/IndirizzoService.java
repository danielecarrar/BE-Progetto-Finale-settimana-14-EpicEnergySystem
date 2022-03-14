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
		Indirizzo i = new Indirizzo();
		i.setVia(indirizzo.getVia());
		i.setCivico(indirizzo.getCivico());
		i.setCap(indirizzo.getCap());
		i.setComune(indirizzo.getComune());
		i.setLocalita(indirizzo.getLocalita());
		return indirizzoRepository.save(i);
	}
	
	public void delete(Long id) {
		if (indirizzoRepository.findById(id).isPresent()) {
			Indirizzo i = indirizzoRepository.findById(id).get();
			i.setComune(null);
			indirizzoRepository.delete(i);
		}
	
	}
	
	public Indirizzo update(Long id, Indirizzo indirizzo) {
		Optional<Indirizzo> result = indirizzoRepository.findById(id);
		if (result.isPresent()) {
			Indirizzo update = result.get();
			update.setCap(indirizzo.getCap());
			update.setCivico(indirizzo.getCivico());
			update.setComune(indirizzo.getComune());
			update.setVia(indirizzo.getVia());
			update.setLocalita(indirizzo.getLocalita());
			update.setId(update.getId());
			return update;
		}
		else {
			throw new EnergySystemException("Indirizzo non presente!");
		}
	}

}
