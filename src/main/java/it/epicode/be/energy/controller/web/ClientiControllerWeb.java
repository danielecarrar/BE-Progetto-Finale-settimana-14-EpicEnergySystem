package it.epicode.be.energy.controller.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		ModelAndView view = new ModelAndView("elencoclienti"); // nome HTML
		view.addObject("listaClienti", clienteService.findAll());

		return view;
	}

	@GetMapping("/mostraformaggiungi")
	public String mostraFormAggiungi(Cliente cliente, Model model) {
		return "formcliente";
	}

	@PostMapping("/aggiungicliente")
	public String aggiungiCliente(@Valid Cliente cliente, BindingResult result, Model model) {
		cliente.setDataInserimento();

		if (result.hasErrors()) {
			return "formcliente";
		}
		clienteService.save(cliente);
		return "redirect:/clienti/mostraelenco";
	}

	@GetMapping("/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {

		Optional<Cliente> clienteTmp = clienteService.findById(id);
		if (clienteTmp.isPresent()) {
			ModelAndView view = new ModelAndView("aggiornacliente"); // HTML
			view.addObject("cliente", clienteTmp.get());
			return view;
		}
		return new ModelAndView("error").addObject("message", "Cliente con id " + id + " non trovato!");
	}

	@PostMapping("/aggiornacliente/{id}")
	public String aggiornaCliente(@PathVariable Long id, Cliente cliente, BindingResult result, Model model) {
		clienteService.update(id, cliente);
		return "redirect:/clienti/mostraelenco";
	}

	@GetMapping("/eliminacliente/{id}")
	public ModelAndView eliminaCliente(@PathVariable Long id, Model model) {
		Optional<Cliente> clienteDaEliminare = clienteService.findById(id);
		if (clienteDaEliminare.isPresent()) {
			clienteService.delete(id);
			ModelAndView view = new ModelAndView("elencoclienti");
			view.addObject("listaClienti", clienteService.findAll());
			return view;
		} else {
			return new ModelAndView("error").addObject("messaggio",
					"Nessun cliente con id " + id + " presente nel database!");
		}
	}
}
