package com.supinfo.leagueAppTdSpringSecurity.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.supinfo.leagueAppTdSpringSecurity.dao.SaisonRepository;
import com.supinfo.leagueAppTdSpringSecurity.entities.Saison;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value="/saison")
public class SaisonController {
	
	@Autowired
	SaisonRepository saisonRepo;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String listerClients(Model model, 
    		@RequestParam(name="page",defaultValue="0")int p,
    		@RequestParam(name="motCle",defaultValue="")String mc) {
    	Page<Saison> pageSaisons = saisonRepo.chercherSaison("%"+mc+"%",PageRequest.of(p,5));
 
    	int pagesCount=pageSaisons.getTotalPages();
    	int[] pages=new int[pagesCount];
    	for(int i=0; i<pagesCount; i++) pages[i]=i;
    	
    	//model permet de stocker tous les résultats que je voudrais afficher
    	model.addAttribute("pages", pages);
    	model.addAttribute("pageCourante", p);
        model.addAttribute("motCle", mc);
        model.addAttribute("pageSaisons", pageSaisons);
        //retourne la vue
        return "listerSaisons";
    }
    
    //Méthode qui permet d'afficher le formulaire de saisie d'une saison
    @RequestMapping(value="/formSaison",method=RequestMethod.GET)
    public String formClient(Model model) {
    	
    	model.addAttribute("saison", new Saison());
    	//retourne la vue
    	return "formSaison";
    }
    
    //Méthode qui créé une saison
    @RequestMapping(value="/SaveSaison",method=RequestMethod.POST)
    public String save(@Valid Saison saison, BindingResult bindingResult, Model model) {
    	//On teste pour voir s'il y a des erreurs dans notre formulaire avant de sauvegarder en base
    	if(bindingResult.hasErrors()) {
    		return "formSaison";
    	}
    	saison.setLibelle("Saison ".concat(saison.getIdentifiantSaison()));
    	/*List<Saison> saisons = saisonRepo.findAll();
    	String errors = "L'identifiant renseigné existe déjà.";
    	for(Saison saison1 : saisons) {
    		if(saison1.getIdentifiantSaison().equals(saison.getIdentifiantSaison()))
    			model.addAttribute("errorMessage", errors);
    			return "formSaison"; 
    	}*/
        
    	saisonRepo.save(saison);
    
    	//retourne la vue
    	return "redirect:index";
    }
    
    @GetMapping("/deleteSaison")
	public String deleteSaison(@RequestParam Long saisonId) {
		saisonRepo.deleteById(saisonId);
		return "redirect:/index";
	}
    
    @GetMapping("/editSaison")
	public String editSaison(@RequestParam Long saisonId, Model model) {
		Saison saison = saisonRepo.findById(saisonId).get();
    	model.addAttribute("saison", saison);
		return "editSaison";
	}
}


