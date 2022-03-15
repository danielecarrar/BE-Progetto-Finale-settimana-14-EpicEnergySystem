package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.energy.service.ClienteService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/clienti")
public class ListClientiControllerWeb {

	@Autowired
	ClienteService clienteService;

	@GetMapping("/mostraelenco")
	public ModelAndView mostraElencoClienti() {
		log.info("Visualizzazione elenco clienti presenti su pagina HTML");
		ModelAndView view = new ModelAndView("elencoclienti");// nome HTML
		view.addObject("listaClienti", clienteService.findAll());
		
		return view;
	}
}
