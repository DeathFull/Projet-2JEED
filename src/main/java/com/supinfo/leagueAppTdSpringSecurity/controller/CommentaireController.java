package com.supinfo.leagueAppTdSpringSecurity.controller;

import com.supinfo.leagueAppTdSpringSecurity.dao.CommentaireRepository;
import com.supinfo.leagueAppTdSpringSecurity.entities.Commentaire;
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
@RequestMapping(value = "/commentaire")
public class CommentaireController {

    @Autowired
    CommentaireRepository commentaireRepository;

    @RequestMapping(value = "/index")
    public String afficherListeCommentaire(Model model,
                                           @RequestParam(name = "page", defaultValue = "0") int p,
                                           @RequestParam(name = "motCle", defaultValue = "") String mc,
                                           @RequestParam int matchId) {
        Page<Commentaire> pageCommentaire = commentaireRepository.searchCommentaire("%" + mc + "%", PageRequest.of(p, 5), matchId);

        int pagesCount = pageCommentaire.getTotalPages();
        int[] pages = new int[pagesCount];
        for (int i = 0; i < pagesCount; i++) pages[i] = i;

        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", p);
        model.addAttribute("motCle", mc);
        model.addAttribute("pageCommentaire", pageCommentaire);
        model.addAttribute("matchId", matchId);

        return "listerCommentaires";
    }

    @PostMapping(value = "/editCommentaire")
    public String editCommentaire(Model model, @RequestParam(name = "matchId") int matchId) {
        model.addAttribute("matchId", matchId);
        return "editCommentaire";
    }

    @PostMapping(value = "/addCommentaire")
    public String addCommentaire(@RequestParam int matchId,
                               @RequestParam String commentaire) {
        Commentaire commentaire1 = new Commentaire(commentaire, matchId);
        commentaireRepository.save(commentaire1);

        return "redirect:/commentaire/index?matchId="+ matchId;
    }
}
