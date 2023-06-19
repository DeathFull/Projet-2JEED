package com.supinfo.leagueAppTdSpringSecurity.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.supinfo.leagueAppTdSpringSecurity.dao.JourneeRepository;
import com.supinfo.leagueAppTdSpringSecurity.dao.SaisonRepository;
import com.supinfo.leagueAppTdSpringSecurity.entities.Journee;
import com.supinfo.leagueAppTdSpringSecurity.entities.Saison;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value="/journee")
public class JourneeController {

	@Autowired
	JourneeRepository journeeRepo;
	
	@Autowired
	SaisonRepository saisonRepo;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String listerJournees(Model model, 
    		@RequestParam(name="page",defaultValue="0")int p,
    		@RequestParam(name="motCle",defaultValue="")String mc) {
    	Page<Journee> pageJournees = journeeRepo.chercherJournee("%"+mc+"%",PageRequest.of(p,5));
    	int pagesCount=pageJournees.getTotalPages();
    	int[] pages=new int[pagesCount];
    	for(int i=0; i<pagesCount; i++) pages[i]=i;
    	
    	//model permet de stocker tous les résultats que je voudrais afficher
    	model.addAttribute("pages", pages);
    	model.addAttribute("pageCourante", p);
        model.addAttribute("motCle", mc);
        model.addAttribute("pageJournees", pageJournees);
        //retourne la vue
        return "listerJournees";
    }
    
    //Méthode qui permet d'afficher le formulaire de saisie d'une journee
    @RequestMapping(value="/formJournee",method=RequestMethod.GET)
    public String formClient(Model model) {
    	//On récupère la liste des saisons qui sera affichée dans le formulaire
    	List<Saison> listeSaisons = saisonRepo.findAll();
    	
    	model.addAttribute("listeSaisons", listeSaisons);
    	model.addAttribute("journee", new Journee());
    	//retourne la vue
    	return "formJournee";
    }
    
    //Méthode qui créé une saison
    @RequestMapping(value="/SaveJournee",method=RequestMethod.POST)
    public String save(@Valid Journee journee, String identifiantSaison, BindingResult bindingResult) {
    	//On teste pour voir s'il y a des erreurs dans notre formulaire avant de sauvegarder en base
    	if(bindingResult.hasErrors()) {
    		return "formJournee";
    	}
    	journeeRepo.save(journee);
    
    	//retourne la vue
    	return "redirect:index";
    }
}
