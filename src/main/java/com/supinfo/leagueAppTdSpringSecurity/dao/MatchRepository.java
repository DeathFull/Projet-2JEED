package com.supinfo.leagueAppTdSpringSecurity.dao;

import com.supinfo.leagueAppTdSpringSecurity.entities.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("select m from Match m where (m.lieu like :x)")
    Page<Match> searchmatchBy(@Param("x") String mc, Pageable pageable);

    @Query("select m from Match m where m.id = :x")
    Match searchById(@Param("x") int id);
}