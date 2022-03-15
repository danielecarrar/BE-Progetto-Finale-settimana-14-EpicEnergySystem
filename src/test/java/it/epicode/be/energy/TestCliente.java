package it.epicode.be.energy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.service.ClienteService;

@AutoConfigureMockMvc
@SpringBootTest
public class TestCliente {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ClienteService clienteService;
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void creaNuovoCliente() throws Exception{
		Cliente c = new Cliente();
		clienteService.save(c);
		assertNotNull(c);
		assertNotNull(clienteService.findAll());
		assertNull(c.getEmail());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void clientiWhenUtenteMockIsAuthenticated() throws Exception {	// non ci sono clienti da eliminare
		this.mockMvc.perform(get("/api/clienti/1")).andExpect(status().isMethodNotAllowed());
	}
	
}
