package it.epicode.be.energy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.Province;
import it.epicode.be.energy.repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepository;

	public Province save(Province provincia) {
		return provinciaRepository.save(provincia);
	}

	public Optional<Province> findBySigla(String sigla) {
		return provinciaRepository.findBySigla(sigla);
	}

	public List<Province> findAll() {
		return provinciaRepository.findAll();
	}

	public Optional<Province> findByNome(String string) {
		return provinciaRepository.findByNome(string);
	}
}
