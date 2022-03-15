package it.epicode.be.energy.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.service.ClienteService;

@RestController
@RequestMapping(path = "/api")
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	// INSERIRE UN NUOVO CLIENTE
	@PostMapping(path = "/clienti")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {

		Cliente nuovoCliente = clienteService.save(cliente); // 201
		return new ResponseEntity<>(nuovoCliente, HttpStatus.CREATED);
	}

	// ELIMINARE UN CLIENTE ESISTENTE (tramite pk, ovvero id)
	@DeleteMapping(path = "/clienti/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return new ResponseEntity<>("Cliente eliminato con successo!", HttpStatus.OK);
	}

	// AGGIORNARE UN CLIENTE ESISTENTE
	@PutMapping(path = "/clienti/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteAggiornato = clienteService.update(id, cliente);
		return new ResponseEntity<>(clienteAggiornato, HttpStatus.OK);

	}

	// RICERCA PER FATTURATO ANNUO
	@GetMapping(path = "/fatturatoannuocliente/{anno}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAllSortedByFatturatoAnnuale(@PathVariable(required = true) int anno,
			Pageable pageable) {
		Page<Cliente> clienteTrovato = clienteService.findAllSortedByFatturatoAnnuale(anno, pageable);

		if (clienteTrovato.hasContent()) {
			return new ResponseEntity<>(clienteTrovato, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// CERCA CLIENTI ORDINANDOLI, utilizzo paginazione
	@GetMapping(path = "/clientiordinati/{page}/{size}/{sort}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAllSorted(@PathVariable(required = true) Integer page,
			@PathVariable(required = true) Integer size, @PathVariable(required = true) String sort,
			Pageable pageable) {
		Page<Cliente> risultato = clienteService.findAllSorted(page, size, sort);

		if (risultato.hasContent()) {
			return new ResponseEntity<>(risultato, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// CERCA CLIENTE PER DATA INSERIMENTO
	@GetMapping(path = "/clientedatainserimento/{gg}/{mm}/{aa}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAllByDataInserimento(@PathVariable(required = true) int gg,
			@PathVariable(required = true) int mm, @PathVariable(required = true) int aa, Pageable pageable) {

		Page<Cliente> clienteTrovato = clienteService.findByDataInserimento(gg, mm, aa, pageable);

		if (clienteTrovato.hasContent()) {
			return new ResponseEntity<>(clienteTrovato, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// CERCA CLIENTE PER SIGLA DELLA PROVINCIA
	@GetMapping(path = "/provinciacliente/{sigla}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAllByProvinciaSigla(@PathVariable(required = true) String sigla,
			Pageable pageable) {

		String siglaUp = sigla.toUpperCase();

		Page<Cliente> risultato = clienteService.findAllByProvinciaSigla(siglaUp, pageable);

		if (risultato.hasContent() && risultato != null) {

			return new ResponseEntity<>(risultato, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// RICERCA CLIENTE PER CORRISPONDENZA PARZIALE DEL NOME
	@GetMapping(path = "/nomeparzialecliente/{nome}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAllByParteNome(@PathVariable(required = true) String nome,
			Pageable pageable) {
		Page<Cliente> clienteTrovato = clienteService.findByParteNome(nome, pageable);

		if (clienteTrovato.hasContent()) {
			return new ResponseEntity<>(clienteTrovato, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/tuttiiclienti")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
		Page<Cliente> tuttiClienti = clienteService.findAll(pageable);

		if (tuttiClienti.hasContent()) {
			return new ResponseEntity<>(tuttiClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
