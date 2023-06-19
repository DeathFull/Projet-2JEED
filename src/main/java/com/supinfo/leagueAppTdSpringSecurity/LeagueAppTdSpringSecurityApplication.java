package com.supinfo.leagueAppTdSpringSecurity;

import com.supinfo.leagueAppTdSpringSecurity.dao.JourneeRepository;
import com.supinfo.leagueAppTdSpringSecurity.dao.MatchRepository;
import com.supinfo.leagueAppTdSpringSecurity.dao.SaisonRepository;
import com.supinfo.leagueAppTdSpringSecurity.entities.Journee;
import com.supinfo.leagueAppTdSpringSecurity.entities.Match;
import com.supinfo.leagueAppTdSpringSecurity.entities.Saison;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.util.Calendar;

@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
public class LeagueAppTdSpringSecurityApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(LeagueAppTdSpringSecurityApplication.class, args);
		
		SaisonRepository saisonRepo = ctx.getBean(SaisonRepository.class);
		JourneeRepository journeeRepo = ctx.getBean(JourneeRepository.class);
		MatchRepository matchRepo = ctx.getBean(MatchRepository.class);
		
		if(saisonRepo.count() == 0) {
			saisonRepo.save(new Saison("Saison 2021-2022", "2021-2022"));
			saisonRepo.save(new Saison("Saison 2022-2023", "2022-2023"));
			saisonRepo.save(new Saison("Saison 2023-2024", "2023-2024"));
		}

		if(journeeRepo.count() == 0) {
			journeeRepo.save(new Journee("10", saisonRepo.findByIdentifiantSaison("2021-2022")));
			journeeRepo.save(new Journee("20", saisonRepo.findByIdentifiantSaison("2021-2022")));
			journeeRepo.save(new Journee("30", saisonRepo.findByIdentifiantSaison("2021-2022")));
			journeeRepo.save(new Journee("40", saisonRepo.findByIdentifiantSaison("2021-2022")));

			journeeRepo.save(new Journee("10", saisonRepo.findByIdentifiantSaison("2022-2023")));
			journeeRepo.save(new Journee("11", saisonRepo.findByIdentifiantSaison("2022-2023")));
			journeeRepo.save(new Journee("12", saisonRepo.findByIdentifiantSaison("2022-2023")));
			journeeRepo.save(new Journee("13", saisonRepo.findByIdentifiantSaison("2022-2023")));
			journeeRepo.save(new Journee("14", saisonRepo.findByIdentifiantSaison("2022-2023")));
			journeeRepo.save(new Journee("15", saisonRepo.findByIdentifiantSaison("2022-2023")));
			journeeRepo.save(new Journee("16", saisonRepo.findByIdentifiantSaison("2022-2023")));
			journeeRepo.save(new Journee("17", saisonRepo.findByIdentifiantSaison("2022-2023")));
			journeeRepo.save(new Journee("18", saisonRepo.findByIdentifiantSaison("2022-2023")));
			journeeRepo.save(new Journee("19", saisonRepo.findByIdentifiantSaison("2022-2023")));
			journeeRepo.save(new Journee("20", saisonRepo.findByIdentifiantSaison("2022-2023")));
		}

		if(matchRepo.count() == 0) {
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(2023, Calendar.JUNE, 18, 9, 0, 0);
			matchRepo.save(new Match(cal.getTime(), "Paris"));

			Calendar cal1 = Calendar.getInstance();
			cal1.clear();
			cal1.set(2023, Calendar.JUNE, 19, 9, 30, 0);
			matchRepo.save(new Match(cal1.getTime(), "Marseille"));

			Calendar cal2 = Calendar.getInstance();
			cal2.clear();
			cal2.set(2023, Calendar.JUNE, 20, 10, 0, 0);
			matchRepo.save(new Match(cal2.getTime(), "Tours"));

			Calendar cal3 = Calendar.getInstance();
			cal3.clear();
			cal3.set(2023, Calendar.JUNE, 21, 10, 30, 0);
			matchRepo.save(new Match(cal3.getTime(), "Lille"));
		}
	}

}
