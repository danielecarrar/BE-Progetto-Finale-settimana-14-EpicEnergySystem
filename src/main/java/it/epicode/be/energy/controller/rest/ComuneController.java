package it.epicode.be.energy.controller.rest;

import java.util.Optional;

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
import it.epicode.be.energy.exceptions.EnergySystemException;
import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.service.ComuneService;

@RestController
@RequestMapping(path = "/api")
@SecurityRequirement(name = "bearerAuth")
public class ComuneController {

	@Autowired
	ComuneService comuneService;

	// RITORNA TUTTA LA LISTA DEI COMUNI PRESENTI (PAGE)
	@GetMapping(path = "/comuni")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Comune>> findAll(Pageable pageable) {
		Page<Comune> listaComuni = comuneService.findAll(pageable);

		if (listaComuni.hasContent()) {
			return new ResponseEntity<>(listaComuni, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// RICERCA COMUNE TRAMITE ID
	@GetMapping(path = "/comuni/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Comune> findById(@PathVariable(required = true) Long id) {
		Optional<Comune> comune = comuneService.findById(id);

		if (comune.isPresent()) {
			return new ResponseEntity<>(comune.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// ELIMINA COMUNE VIA ID
	@DeleteMapping(path = "/comuni/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			comuneService.delete(id);
		} catch (Exception e) {
			throw new EnergySystemException("Comune con id " + id
					+ " non eliminato! Causa: Non è possibile eliminare un comune che referenzia delle fatture attive!");
		}
		return new ResponseEntity<>("Comune eliminato", HttpStatus.OK);
	}

	// INSERIRE NUOVO COMUNE
	@PostMapping(path = "/comuni")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Comune> save(@RequestBody Comune comune) {
		Comune save = comuneService.save(comune);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	// AGGIORNARE COMUNE ESISTENTE
	@PutMapping(path = "/comuni/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Comune> update(@PathVariable Long id, @RequestBody Comune comune) {
		Comune comuneDaAggiornare = comuneService.update(id, comune);
		return new ResponseEntity<>(comuneDaAggiornare, HttpStatus.OK);
	}
}
