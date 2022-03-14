package it.epicode.be.energy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.repository.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepo;

	public Comune save(Comune comune) {
		return comuneRepo.save(comune);
	}

	public Optional<Comune> findByNome(String nome) {
		return comuneRepo.findByNome(nome);
	}

	public Page<Comune> findAll(Pageable pageable) {
		return comuneRepo.findAll(pageable);
	}

	public Optional<Comune> findById(Long id) {
		return comuneRepo.findById(id);
	}

	public void delete(Long id) {
		if (comuneRepo.findById(id).isPresent()) {
			Comune c = comuneRepo.findById(id).get();
			c.setProvincia(null);
			comuneRepo.delete(c);
		}

	}

	public Comune update(Long id, Comune comune) {
		Optional<Comune> comuneResult = comuneRepo.findById(id);
		if (comuneResult.isPresent()) {
			Comune update = comuneResult.get();
			update.setNome(comune.getNome());
			update.setProvincia(comune.getProvincia());

			return comuneRepo.save(update);
		}
		return null; // TODO implementare eccezione relativa.
	}

}
