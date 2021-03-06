package it.epicode.be.energy.util.fileloader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.model.Province;
import it.epicode.be.energy.service.ComuneService;
import it.epicode.be.energy.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataSourceLoader implements CommandLineRunner {

	@Autowired
	ProvinciaService provinciaService;

	@Autowired
	ComuneService comuneService;

	@Override
	public void run(String... args) throws Exception {
		log.info("Dati province e comuni pronti per essere caricati!!");
	}

	public void caricaProvince() {
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader("province-italiane.csv")).withCSVParser(csvParser)
				.withSkipLines(1).build()) {
			String[] values = null;

			while ((values = reader.readNext()) != null) {

				provinciaService.save(new Province(values[0], values[1], values[2]));
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void caricaComuni() {
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader("comuni-italiani.csv")).withCSVParser(csvParser)
				.withSkipLines(1).build()) {
			String[] values = null;
			while ((values = reader.readNext()) != null) {

				Optional<Province> p = provinciaService.findByNome(values[3]);
				if (!p.isEmpty()) {
					comuneService.save(new Comune(values[2], p.get()));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}