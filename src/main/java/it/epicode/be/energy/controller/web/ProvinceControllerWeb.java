package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.energy.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/province")
public class ProvinceControllerWeb {

	@Autowired
	ProvinciaService provinceService;

	@GetMapping("/mostraprovince")
	public ModelAndView mostraElencoProvince() {
		log.info("Visualizzazione elenco province presenti su pagina HTML");
		ModelAndView view = new ModelAndView("elencoprovince");
		view.addObject("listaProvince", provinceService.findAll());

		return view;
	}
}
