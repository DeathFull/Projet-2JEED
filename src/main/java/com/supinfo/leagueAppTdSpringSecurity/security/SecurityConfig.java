package com.supinfo.leagueAppTdSpringSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*
 * Partie Spring security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
     * Permet de stocker le mot de pass sous forme de hash en mémoire au lieu que ce soit visible
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //Définition de ma liste d'users et de rôles à partir du inMemoryAuthentification (Authentification des users en mémoire
    /*
     * noop = No password encoder
     */
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("journaliste").password(passwordEncoder().encode("1234")).roles("JOURNALISTE").build(),
                User.withUsername("member-league").password(passwordEncoder().encode("1234")).roles("MEMBER-LEAGUE").build());
    }


    //Utilisation de l'annotation 'Bean' pour que la méthode s'exécute au démarrage
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers("/index").authenticated()
                        .requestMatchers("/match/**").hasAnyRole("JOURNALISTE", "MEMBER-LEAGUE")
                        .requestMatchers("/commentaire/**").hasRole("JOURNALISTE")
                        .requestMatchers("/evenement/**").hasRole("JOURNALISTE")
                        .requestMatchers("/report/**").hasRole("MEMBER-LEAGUE")
                        .requestMatchers("/suspendre/**").hasRole("MEMBER-LEAGUE")
                        .requestMatchers("/error").authenticated()
                        .requestMatchers("/journee/**").hasRole("JOURNALISTE")
                        .requestMatchers("/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.successHandler((req, res, auth) -> res.sendRedirect("/index")))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));
        return httpSecurity.build();
    }

}
