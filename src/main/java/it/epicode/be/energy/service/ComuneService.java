package it.epicode.be.energy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.model.Province;
import it.epicode.be.energy.repository.ComuneRepository;
import it.epicode.be.energy.repository.ProvinciaRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;
	
	@Autowired
	ProvinciaRepository provinciaRepository;

	public Comune save(Comune comune) {
		return comuneRepository.save(comune);
	}

	public Optional<Comune> findByNome(String nome) {
		return comuneRepository.findByNome(nome);
	}

	public Page<Comune> findAll(Pageable pageable) {
		return comuneRepository.findAll(pageable);
	}

	public Optional<Comune> findById(Long id) {
		return comuneRepository.findById(id);
	}

	public void delete(Long id) {
		if (comuneRepository.findById(id).isPresent()) {
			Comune comune = comuneRepository.findById(id).get();
			Province provincia = provinciaRepository.getById(id);
			
			provinciaRepository.delete(provincia);
			comuneRepository.delete(comune);
		}
	}

	public Comune update(Long id, Comune comuneInserito) {
		Optional<Comune> comune = comuneRepository.findById(id);
		
		if (comune.isPresent()) {
			Comune comuneDaAggiornare = comune.get();
			comuneDaAggiornare.setNome(comuneInserito.getNome());
			comuneDaAggiornare.setProvincia(comuneInserito.getProvincia());

			return comuneRepository.save(comuneDaAggiornare);
		}
		return null;
	}
}
