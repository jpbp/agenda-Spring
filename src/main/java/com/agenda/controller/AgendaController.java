package com.agenda.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.agenda.models.Agenda;
import com.agenda.repository.AgendaRepository;
import com.sun.xml.bind.v2.model.core.ID;


@Controller
public class AgendaController {
	
	@Autowired
	private AgendaRepository ar;
	
	@RequestMapping(value="/cadastrarAgenda",method = RequestMethod.GET)
	public String form() {
		return "formAgenda";
	}

	@RequestMapping(value="/cadastrarAgenda",method = RequestMethod.POST)
	public String form(Agenda agenda) {
		ar.save(agenda);
		return "redirect:/cadastrarAgenda";
	}
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Validated Agenda agenda,
	  BindingResult result, Model model) {
		agenda.setCodigo(id);

	    ar.save(agenda);
	    return "redirect:/agenda";
	}
	
	@RequestMapping("/deletarContato")
	public String deletarContato(long codigo){
		Agenda agenda = ar.findByCodigo(codigo);
		ar.delete(agenda);
		return "redirect:/agenda";
	}
	
	@RequestMapping(value="/formAltera",method = RequestMethod.GET)
	public String showUpdateForm(long id,Model model) {
	    Agenda agenda = ar.findByCodigo(id);

	    model.addAttribute("agenda",agenda);
	    
	    return "formAltera";
	}
	
	@RequestMapping(value="/agenda",method = RequestMethod.POST)
	public ModelAndView BuscaContato(String nome) {
		//ele vai renderiza de acordo com os dados do evento
		ModelAndView mv = new ModelAndView("index");
		Iterable<Agenda> agenda;
		if(nome.isEmpty()) {
			agenda= ar.findByOrderByNomeAsc();
		}else {
			Iterable<Agenda> aux;
			List<Agenda> ag = new ArrayList<>();
			aux= ar.findByNomeStartsWith(nome);
			aux.forEach(element->{
				ag.add(element);
			});
			Collections.sort(ag);
			agenda=ag;
		}
		// objeto para recuparar os contatos no banco
		//passando a lista de contato para o index 
		mv.addObject("agenda",agenda);
		return mv;
	}
	
	
	@RequestMapping(value="/agenda")
	public ModelAndView listaAgenda() {
		//ele vai renderiza de acordo com os dados do evento
		ModelAndView mv = new ModelAndView("index");
		// objeto para recuparar os contatos no banco
		Iterable<Agenda> agenda= ar.findByOrderByNomeAsc();
		//passando a lista de contato para o index 
		mv.addObject("agenda",agenda);
		return mv;
	}
	
	
}
