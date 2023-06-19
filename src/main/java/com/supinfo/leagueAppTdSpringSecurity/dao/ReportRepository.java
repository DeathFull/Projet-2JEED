package com.supinfo.leagueAppTdSpringSecurity.dao;

import com.supinfo.leagueAppTdSpringSecurity.entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

    @Query("select t from Report t where t.matchId = :x")
    public Page<Report> searchReport(Pageable pageable, @Param("x")int id);

}
