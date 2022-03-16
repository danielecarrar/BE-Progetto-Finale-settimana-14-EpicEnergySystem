package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.energy.service.FatturaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/fatture")
public class FattureControllerWeb {

	@Autowired
	FatturaService fattureService;
	
	@GetMapping("/mostrafatture")
	public ModelAndView mostraElencoComuni() {
		log.info("Visualizzazione elenco fatture presenti su pagina HTML");
		ModelAndView view = new ModelAndView("elencofatture");
		view.addObject("listaFatture", fattureService.findAll());

		return view;
	}
}
