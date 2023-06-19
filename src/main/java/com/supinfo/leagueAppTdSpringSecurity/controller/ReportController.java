package com.supinfo.leagueAppTdSpringSecurity.controller;

import com.supinfo.leagueAppTdSpringSecurity.dao.MatchRepository;
import com.supinfo.leagueAppTdSpringSecurity.dao.ReportRepository;
import com.supinfo.leagueAppTdSpringSecurity.entities.Match;
import com.supinfo.leagueAppTdSpringSecurity.entities.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    MatchRepository matchRepository;

    @RequestMapping(value = "/index")
    public String afficherReport(Model model, RedirectAttributes redirectAttributes,
                               @RequestParam(name = "page", defaultValue = "0") int p,
                               @RequestParam(name = "motCle", defaultValue = "") String mc,
                               @RequestParam int matchId) {
        Match match = matchRepository.searchById(matchId);
        if (!match.getStatut().equalsIgnoreCase("waiting")) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vous ne pouvez pas reporter un match démarré/fini !");
            return "redirect:../match/index";
        }
        Page<Report> pageReport = reportRepository.searchReport(PageRequest.of(p, 5), matchId);

        int pagesCount = pageReport.getTotalPages();
        int[] pages = new int[pagesCount];
        for (int i = 0; i < pagesCount; i++) pages[i] = i;

        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", p);
        model.addAttribute("motCle", mc);
        model.addAttribute("pageReport", pageReport);
        model.addAttribute("matchId", matchId);

        return "listerReport";
    }

    @PostMapping(value = "/editReport")
    public String editReport(Model model,
                             @RequestParam int matchId) {
        model.addAttribute("matchId", matchId);
        return "editReport";
    }

    @PostMapping(value = "/addReport")
    public String addReport(@RequestParam int matchId,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date newDate,
                            @RequestParam String reason) {
        Match match = matchRepository.searchById(matchId);
        Report report = new Report(match.getDate(), newDate, reason, matchId);
        match.setDate(newDate);
        reportRepository.save(report);

        return "redirect:/report/index?matchId=" + matchId;
    }
}
