package com.supinfo.leagueAppTdSpringSecurity.controller;

import com.supinfo.leagueAppTdSpringSecurity.dao.MatchRepository;
import com.supinfo.leagueAppTdSpringSecurity.dao.SuspendreRepository;
import com.supinfo.leagueAppTdSpringSecurity.entities.Match;
import com.supinfo.leagueAppTdSpringSecurity.entities.Suspendre;
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
@RequestMapping(value = "/suspendre")
public class SuspendreController {

    @Autowired
    SuspendreRepository suspendreRepository;

    @Autowired
    MatchRepository matchRepository;

    @RequestMapping(value = "/index")
    public String listersuspendre(Model model, RedirectAttributes redirectAttributes,
                                  @RequestParam(name = "page", defaultValue = "0") int p,
                                  @RequestParam(name = "motCle", defaultValue = "") String mc,
                                  @RequestParam int matchId) {
        Match match = matchRepository.searchById(matchId);
        if (!match.getStatut().equalsIgnoreCase("started")) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vous ne pouvez pas suspendre un match pas commencé/fini !");
            return "redirect:../match/index";
        }
        Page<Suspendre> pagesuspendre = suspendreRepository.searchsuspendre(PageRequest.of(p, 5), p);

        int pagesCount = pagesuspendre.getTotalPages();
        int[] pages = new int[pagesCount];
        for (int i = 0; i < pagesCount; i++) pages[i] = i;

        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", p);
        model.addAttribute("motCle", mc);
        model.addAttribute("pagesuspendre", pagesuspendre);
        model.addAttribute("matchId", matchId);

        return "listersuspendre";
    }

    @PostMapping(value = "/editSuspendre")
    public String editSuspendre(Model model,
                                @RequestParam int matchId) {
        model.addAttribute("matchId", matchId);
        return "editSuspendre";
    }

    @PostMapping(value = "/addSuspendre")
    public String addSuspendre(@RequestParam int matchId,
                                  @RequestParam String raison) {
        Match match = matchRepository.searchById(matchId);
        Suspendre suspendre = new Suspendre(raison, "Suspendu");
        match.setStatut("Suspendu - " + raison);
        suspendreRepository.save(suspendre);

        return "redirect:/suspendre/index?matchId=" + matchId;
    }
}
