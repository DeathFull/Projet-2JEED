package com.supinfo.leagueAppTdSpringSecurity.dao;

import com.supinfo.leagueAppTdSpringSecurity.entities.Commentaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
    @Query("select e from Commentaire e where e.Commentaire like :x and e.matchId = :i")
    public Page<Commentaire> searchCommentaire(@Param("x") String mc, Pageable pageable, @Param("i") int id);

    @Query("select t from Commentaire t where t.matchId = :x")
    public Commentaire searchid(@Param("x")int id);
}
