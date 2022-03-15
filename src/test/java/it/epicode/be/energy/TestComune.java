package it.epicode.be.energy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.model.Province;
import it.epicode.be.energy.repository.ComuneRepository;
import it.epicode.be.energy.repository.ProvinciaRepository;
import it.epicode.be.energy.util.fileloader.DataSourceLoader;

@SpringBootTest
public class TestComune {

	@Autowired
	ComuneRepository comuneRepository;

	@Autowired
	DataSourceLoader dataLoader;

	@Autowired
	ProvinciaRepository provinceRepository;

	@Test
	public void beansTest() throws Exception {
		assertNotNull(dataLoader);
		assertNotNull(comuneRepository);
		assertNotNull(provinceRepository);
	}

	@Test
	public void testComune() throws Exception {
		Province provinciaTest = new Province("VE", "Venezia", "Veneto");
		Comune comuneTest = new Comune();
		comuneTest.setProvincia(provinciaTest);
		assertNotNull(comuneRepository.save(comuneTest));
		assertNotEquals(provinciaTest, comuneTest);
		assertNull(comuneTest.getNome());
	}

	@Test
	public void testProvinceDb() throws Exception {
		dataLoader.caricaProvince();
		List<Province> listaProvince = provinceRepository.findAll();
		assertNotNull(listaProvince);
	}
}