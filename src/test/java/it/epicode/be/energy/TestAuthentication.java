package it.epicode.be.energy;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import it.epicode.be.energy.service.ClienteService;

@AutoConfigureMockMvc
@SpringBootTest
public class TestAuthentication {

	@Autowired
	private MockMvc mockMvc;

	// Test indica che non è possibile fare il login senza dati di login aggiunti
	@Test
	@WithAnonymousUser
	public void loginSenzaBody() throws Exception {
		this.mockMvc.perform(post("/auth/login")).andExpect(status().isBadRequest());
	}

	// Test che indica che l'utente Mock NON è autenticato
	@Test
	@WithAnonymousUser
	public void testClienti() throws Exception {
		this.mockMvc.perform(get("/api/cliente")).andExpect(status().isUnauthorized());
	}

	// Test conferma che non ci sono clienti nel db e che l'utente Mock è
	// autenticato
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void listaClientiWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/tuttiiclienti")).andExpect(status().isNotFound());
	}

	// Test controller utente autenticato
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void comuniWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/aggiungicomuni")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void provinceWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/aggiungiprovince")).andExpect(status().isOk());
	}
}
