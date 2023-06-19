package com.supinfo.leagueAppTdSpringSecurity.controller;

import com.supinfo.leagueAppTdSpringSecurity.dao.MatchRepository;
import com.supinfo.leagueAppTdSpringSecurity.entities.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/match")
public class MatchController {

    @Autowired
    MatchRepository matchRepo;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String listermatch(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int p,
                              @RequestParam(name = "motCle", defaultValue = "") String mc) {
        Page<Match> pageMatch = matchRepo.searchmatchBy("%" + mc + "%", PageRequest.of(p, 5));

        int pagesCount = pageMatch.getTotalPages();
        int[] pages = new int[pagesCount];
        for (int i = 0; i < pagesCount; i++) pages[i] = i;

        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", p);
        model.addAttribute("motCle", mc);
        model.addAttribute("pageMatches", pageMatch);

        return "listerMatch";
    }

    @RequestMapping(value = "/editStatut")
    public String editStatut(@RequestParam int matchId, Model model) {
        model.addAttribute("matchId", matchId);
        return "editStatut";
    }


    @PostMapping(value = "/changeStatut")
    public String changeStatut(@RequestParam int matchId, @RequestParam String statut, Model model, RedirectAttributes redirectAttributes) {
        Match match = matchRepo.searchById(matchId);
        if (match.getStatut().equalsIgnoreCase(statut)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Impossible de changer le statut par lui-même !");
            return "redirect:index";
        }
        match.setStatut(statut);
        matchRepo.save(match);

        return "redirect:index";
    }

    @RequestMapping(value = "/editScore")
    public String editScore(@RequestParam int matchId, Model model) {
        model.addAttribute("matchId", matchId);
        return "editScore";
    }


    @PostMapping(value = "/changeScore")
    public String changeScore(@RequestParam int matchId, @RequestParam String score, Model model) {
        Match match = matchRepo.searchById(matchId);
        match.setScore(score);
        matchRepo.save(match);

        return "redirect:index";
    }
}