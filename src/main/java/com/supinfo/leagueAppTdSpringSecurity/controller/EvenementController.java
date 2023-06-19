package com.supinfo.leagueAppTdSpringSecurity.controller;

import com.supinfo.leagueAppTdSpringSecurity.dao.EvenementRepository;
import com.supinfo.leagueAppTdSpringSecurity.entities.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/evenement")
public class EvenementController {

    @Autowired
    EvenementRepository evenementRepository;

    @RequestMapping(value = "/index")
    public String listerevenement(Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int p,
                                  @RequestParam(name = "motCle", defaultValue = "") String mc,
                                  @RequestParam(name = "page1", defaultValue = "0") int p1,
                                  @RequestParam(name = "motCle1", defaultValue = "") String mc1,
                                  @RequestParam int matchId) {
        Page<Evenement> pagebut = evenementRepository.searchType("%but%", PageRequest.of(p, 5));

        int pagesCount = pagebut.getTotalPages();
        int[] pages = new int[pagesCount];
        for (int i = 0; i < pagesCount; i++) pages[i] = i;

        Page<Evenement> pagecarton = evenementRepository.searchType("%Card%", PageRequest.of(p1, 5));

        System.out.println(pagecarton.getNumberOfElements());
        int pagesCount1 = pagecarton.getTotalPages();
        int[] pages1 = new int[pagesCount1];
        for (int i = 0; i < pagesCount1; i++) pages1[i] = i;

        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", p);
        model.addAttribute("motCle", mc);
        model.addAttribute("pages1", pages1);
        model.addAttribute("pageCourante1", p1);
        model.addAttribute("motCle1", mc1);
        model.addAttribute("pagebut", pagebut);
        model.addAttribute("matchId", matchId);
        model.addAttribute("pagecarton", pagecarton);

        return "listerevenement";
    }

    @PostMapping(value = "/editCarton")
    public String editEvenement(Model model,
                             @RequestParam int matchId) {
        model.addAttribute("matchId", matchId);
        return "editCarton";
    }

    @PostMapping(value = "/edit")
    public String updateEvenement(@RequestParam int matchId,
                                  @RequestParam String type,
                                  @RequestParam String player,
                                  @RequestParam int gameTime) {
        Evenement event = new Evenement(type, player, gameTime, matchId);
        evenementRepository.save(event);

        return "redirect:/evenement/index?matchId=" + matchId;
    }
}
