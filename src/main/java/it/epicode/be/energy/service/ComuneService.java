package it.epicode.be.energy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.model.Province;
import it.epicode.be.energy.repository.ClienteRepository;
import it.epicode.be.energy.repository.ComuneRepository;
import it.epicode.be.energy.repository.IndirizzoRepository;
import it.epicode.be.energy.repository.ProvinciaRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;

	@Autowired
	IndirizzoRepository indirizzoRepository;

	@Autowired
	ProvinciaRepository provinciaRepository;

	@Autowired
	ClienteRepository clienteRepository;

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

	public List<Comune> findAll() {
		return comuneRepository.findAll();
	}
	
	public void delete(Long id) {
		if (comuneRepository.findById(id).isPresent()) {
			Comune comune = comuneRepository.findById(id).get();
			Optional<Province> provincia = provinciaRepository.findById(id);
			Optional<Indirizzo> indirizzo = indirizzoRepository.findById(id);
			Optional<Cliente> cliente = clienteRepository.findById(id);
			
			comune.setProvincia(null);
			
			if (cliente.isPresent()) {
				cliente.get().setSedeLegale(null);
				cliente.get().setSedeOperativa(null);
			}
			if (indirizzo.isPresent()) {
				indirizzo.get().setComune(null);
			}
			if (provincia.isPresent()) {
			}
			comuneRepository.delete(comune);
		}
	}
}
