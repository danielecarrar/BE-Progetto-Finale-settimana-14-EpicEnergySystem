package it.epicode.be.energy.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exceptions.EnergySystemException;
import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.repository.ClienteRepository;
import it.epicode.be.energy.repository.FatturaRepository;
import it.epicode.be.energy.repository.IndirizzoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	FatturaRepository fatturaRepository;

	@Autowired
	IndirizzoRepository indirizzoRepository;

	public Page<Cliente> findByDataInserimento(int giorno, int mese, int anno, Pageable pageable) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(anno, mese, giorno);
			Date data = calendar.getTime();

			return clienteRepository.findAllByDataInserimento(data, pageable);
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Cliente> findByDataUltimoContatto(int giorno, int mese, int anno, Pageable pageable) {
		try {
			// creo e valorizzo la data da cercare con i parametri in input
			Calendar calendario = Calendar.getInstance();
			calendario.set(anno, mese, giorno);
			Date data = calendario.getTime();

			return clienteRepository.findAllByDataUltimoContatto(data, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	// metodo che permette di ricercare un cliente anche con una parte del nome
	public Page<Cliente> findByParteNome(String nome, Pageable pageable) {
		try {
			Page<Cliente> clienti = clienteRepository.findByParteNome(nome, pageable);

			if (clienti.hasContent()) {
				return clienti;
			}
			log.error("Non ci sono risultati da visualizzare, riprova");
			return null;
		} catch (Exception e) {

			e.printStackTrace();

			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Cliente> findAllSorted(Integer page, Integer size, String sort) {
		try {
			String[] attributi = { "id", "ragioneSociale", "dataInserimento", "dataUltimoContatto" };
			if (Arrays.stream(attributi).anyMatch(sort::equals)) {
				Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
				Page<Cliente> pageResult = clienteRepository.findAll(pageable);
				if (pageResult.hasContent()) {
					return pageResult;
				} else
					return null;
			}
			log.error("Nessuna corrispondenza trovata");
			return null;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	public Page<Cliente> findAllSortedByFatturatoAnnuale(int anno, Pageable pageable) {
		try {
			return clienteRepository.findAllSortedByFatturatoAnnuale(anno, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public Page<Fattura> findFatturaByIdCliente(Long IDCliente, Pageable pageable) {
		try {
			return fatturaRepository.findFatturaByIdCliente(IDCliente, pageable);
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void delete(Long id) {
		if (clienteRepository.findById(id).isPresent()) {
			Cliente daEliminare = clienteRepository.findById(id).get();

			// setto a null i valori del cliente, per evitare errori ed eliminare senza
			// utilizzo di cascade

			daEliminare.setSedeLegale(null);

			daEliminare.setSedeOperativa(null);

			clienteRepository.deleteById(id);
		} else {
			throw new EnergySystemException("Operazione non riuscita! Nessun risultato");
		}

	}

	public Cliente update(Long id, Cliente cliente) {
		Optional<Cliente> clienteResult = clienteRepository.findById(id);
		if (clienteResult.isPresent()) {
			Cliente clienteAggiornato = clienteResult.get();
			clienteAggiornato.setRagioneSociale(cliente.getRagioneSociale());
			clienteAggiornato.setDataInserimento(cliente.getDataInserimento());
			clienteAggiornato.setDataUltimoContatto(cliente.getDataUltimoContatto());
			clienteAggiornato.setIva(cliente.getIva());
			clienteAggiornato.setNomeContatto(cliente.getNomeContatto());
			clienteAggiornato.setCognomeContatto(cliente.getCognomeContatto());
			clienteAggiornato.setEmailContatto(cliente.getEmailContatto());
			clienteAggiornato.setNumeroContatto(cliente.getNumeroContatto());
			clienteAggiornato.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
			clienteAggiornato.setPec(cliente.getPec());
			clienteAggiornato.setEmail(cliente.getEmail());
			clienteAggiornato.setSedeLegale(cliente.getSedeLegale());
			clienteAggiornato.setSedeOperativa(cliente.getSedeOperativa());
			clienteAggiornato.setTelefono(cliente.getTelefono());
			clienteAggiornato.setTipologiaCliente(cliente.getTipologiaCliente());
			return clienteRepository.save(clienteAggiornato);
		}
		return null;
	}

	public Page<Cliente> findAllByProvinciaSigla(String sigla, Pageable pageable) {
		try {
			Page<Cliente> clienti = clienteRepository.findAllBySedeLegaleComuneProvinciaSigla(sigla, pageable);
			if (clienti.hasContent()) {
				return clienti;
			}
			log.error("Nessun risultato in nessuna provincia!");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Cliente> findByOrderByNomeContattoAsc(Pageable pageable) {
		try {
			Page<Cliente> clienti = clienteRepository.findByOrderByNomeContattoAsc(pageable);
			if (clienti.hasContent()) {
				return clienti;
			}
			log.error("Nessun risultato! Riprova");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
}
