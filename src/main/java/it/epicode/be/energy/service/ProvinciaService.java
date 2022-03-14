package it.epicode.be.energy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.Provincia;
import it.epicode.be.energy.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepo;

	public Provincia save(Provincia provincia) {
		return provinciaRepo.save(provincia);
	}

	public Optional<Provincia> findBySigla(String sigla) {
		return provinciaRepo.findBySigla(sigla);
	}
}
