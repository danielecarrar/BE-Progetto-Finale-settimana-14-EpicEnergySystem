package it.epicode.be.energy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.repository.FatturaRepository;

@SpringBootTest
public class TestFatture {
	
	@Autowired
	FatturaRepository fatturaRepository;

	@Test
	public void testFattura() throws Exception {
		Fattura f = new Fattura();
		f.setAnno(2020);
		fatturaRepository.save(f);
		assertNotNull(f.getAnno());
		assertEquals(null, f.getCliente());
	}
}
