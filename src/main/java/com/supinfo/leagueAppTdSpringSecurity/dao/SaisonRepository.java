package com.supinfo.leagueAppTdSpringSecurity.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.supinfo.leagueAppTdSpringSecurity.entities.Saison;

@Repository
public interface SaisonRepository extends JpaRepository<Saison,Long> {
	
	public List<Saison> findByLibelle(String n);
	
	public Saison findByIdentifiantSaison(String n);
	
	@Query("select e from Saison e where e.libelle like :x")
	public Page<Saison> chercherSaison(@Param("x") String mc, Pageable pageable);


}
