package it.epicode.be.energy.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.energy.util.fileloader.DataSourceLoader;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class DataLoaderController {

	@Autowired
	DataSourceLoader loader;
	
	@GetMapping("/province")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<String> aggiungiProvince(){
		loader.caricaProvince();
		return new ResponseEntity<>("Caricamento province italiane completato!!", HttpStatus.OK);
	}
	
	@GetMapping("/comuni")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<String> aggiungiComuni(){
		loader.caricaComuni();
		return new ResponseEntity<>("Caricamento di oltre 7000 comuni italiani completato!!", HttpStatus.OK);
	}
}
