package it.epicode.be.energy.controller.rest;

import java.math.BigDecimal;

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
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.service.FatturaService;

@RestController
@RequestMapping(path = "/api")
@SecurityRequirement(name = "bearerAuth")
public class FatturaController {

	@Autowired
	FatturaService fatturaService;

	// TROVA FATTURE FILTRANDOLE PER STATO
	@GetMapping(path = "/statofattura/{stato}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findByStato(@PathVariable(required = true) String stato, Pageable pageable) {
		Page<Fattura> risultato = fatturaService.findByStato(stato, pageable);

		if (risultato.hasContent()) {
			return new ResponseEntity<>(risultato, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// INSERIRE NUOVA FATTURA
	@PostMapping(path = "/fattura")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Fattura> save(@RequestBody Fattura fattura) {
		Fattura fatturaDaSalvare = fatturaService.save(fattura);
		return new ResponseEntity<>(fatturaDaSalvare, HttpStatus.CREATED);
	}

	// AGGIORNARE FATTURA ESISTENTE
	@PutMapping(path = "/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Fattura> update(@PathVariable Long id, @RequestBody Fattura fattura) {
		Fattura fatturaAggiornata = fatturaService.update(id, fattura);
		return new ResponseEntity<>(fatturaAggiornata, HttpStatus.OK);
	}

	// ELIMINARE FATTURA VIA ID
	@DeleteMapping(path = "/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		fatturaService.delete(id);
		return new ResponseEntity<>("Fattura eliminata!", HttpStatus.OK);
	}

	// TROVA FATTURA TRAMITE ID DEL CLIENTE
	@GetMapping(path = "/fatturaid/{IDCliente}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findFatturaByClienteId(@PathVariable(required = true) Long IDCliente,
			Pageable pageable) {
		Page<Fattura> cliente = fatturaService.findFatturaByClienteId(IDCliente, pageable);

		if (cliente.hasContent()) {
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// TROVA LE FATTURE IN UN RANGE DI IMPORTI
	@GetMapping(path = "/rangefattura/{importoMin}/{importoMax}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findByRange(@PathVariable(required = true) BigDecimal importoMin,
			@PathVariable(required = true) BigDecimal importoMax, Pageable pageable) {

		Page<Fattura> rangeFattura = fatturaService.findByRange(importoMin, importoMax, pageable);

		if (rangeFattura.hasContent()) {
			return new ResponseEntity<>(rangeFattura, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// TROVA TUTTE LE FATTURE DISPONIBILI
	@GetMapping(path = "/fatture")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findAll(Pageable pageable) {

		Page<Fattura> risultato = fatturaService.findAll(pageable);
		if (risultato.hasContent()) {
			return new ResponseEntity<>(risultato, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
