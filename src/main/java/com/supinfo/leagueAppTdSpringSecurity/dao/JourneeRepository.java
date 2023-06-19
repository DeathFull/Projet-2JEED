package com.supinfo.leagueAppTdSpringSecurity.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.supinfo.leagueAppTdSpringSecurity.entities.Journee;


@Repository
public interface JourneeRepository extends JpaRepository<Journee,Long> {

	public List<Journee> findBynumJournee(String n);

	
	@Query("select e from Journee e where e.numJournee like :x")
	public Page<Journee> chercherJournee(@Param("x") String mc, Pageable pageable);

}
