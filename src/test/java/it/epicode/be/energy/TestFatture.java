package it.epicode.be.energy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import it.epicode.be.energy.controller.rest.FatturaController;
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.repository.ClienteRepository;
import it.epicode.be.energy.repository.FatturaRepository;

@AutoConfigureMockMvc
@SpringBootTest
public class TestFatture {

	@Autowired
	FatturaRepository fatturaRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	FatturaController fatturaController;

	@Autowired
	ClienteRepository clienteRepository;

	@Test
	public void testFattura() throws Exception {
		Fattura f = new Fattura();
		f.setAnno(2020);
		fatturaRepository.save(f);
		assertNotNull(f.getAnno());
		assertEquals(null, f.getCliente());
	}

	@Test
	public void testAutoDataFattura() throws Exception {
		Fattura f = new Fattura();
		f.setData();
		assertNotNull(f.getData());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void testControllerFatture() throws Exception {
		this.mockMvc.perform(get("/api/fatture")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void testControllerFattureSave() throws Exception {
		Fattura test2 = new Fattura();
		fatturaRepository.save(test2);
		assertNotNull(fatturaRepository.findById(test2.getId()));
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void testControllerFattureFindAll() throws Exception {
		Fattura test2 = new Fattura();
		fatturaRepository.save(test2);
		assertNotNull(fatturaRepository.findAll());
	}
}
