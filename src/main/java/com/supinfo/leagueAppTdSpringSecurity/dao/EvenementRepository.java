package com.supinfo.leagueAppTdSpringSecurity.dao;

import com.supinfo.leagueAppTdSpringSecurity.entities.Evenement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement,Long> {

    @Query("select t from Evenement t where t.id = :x")
    public Evenement searchid(@Param("x")int id);

    @Query("select t from Evenement t where t.type like :x")
    public Page<Evenement> searchType(@Param("x")String type, Pageable pageable);
}
