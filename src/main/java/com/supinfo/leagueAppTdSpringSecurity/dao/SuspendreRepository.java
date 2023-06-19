package com.supinfo.leagueAppTdSpringSecurity.dao;

import com.supinfo.leagueAppTdSpringSecurity.entities.Suspendre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspendreRepository extends JpaRepository<Suspendre, Long> {

    @Query("select t from Suspendre t where t.id = :x")
    public Page<Suspendre> searchsuspendre(Pageable pageable, @Param("x") int id);

}
