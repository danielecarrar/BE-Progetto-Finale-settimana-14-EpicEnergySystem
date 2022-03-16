package it.epicode.be.energy.controller.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.service.ClienteService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/clienti")
public class ClientiControllerWeb {

	@Autowired
	ClienteService clienteService;

	@GetMapping("/mostraelenco")
	public ModelAndView mostraElencoClienti() {
		log.info("Visualizzazione elenco clienti presenti su pagina HTML");
		ModelAndView view = new ModelAndView("elencoclienti");// nome HTML
		view.addObject("listaClienti", clienteService.findAll());

		return view;
	}

	@GetMapping("/mostraformaggiungi")
	public String mostraFormAggiungi(Cliente cliente, Model model) {
		log.info("form aggiunta di un cliente");
		return "formcliente";
	}

	@PostMapping("/addcliente")
	public String aggiungiCliente(Cliente cliente, BindingResult result, Model model) {
		log.info("aggiunta cliente");
		if (result.hasErrors()) {
			
			return "formcliente";
		}
		clienteService.save(cliente);
		return "redirect:/clienti/mostraelenco";
	}
}
