package com.supinfo.leagueAppTdSpringSecurity.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@Table(name = "MatchCommentaire")
public class Commentaire {
    private static final long serialVersionUID = 1L;

    @Id
    @jakarta.persistence.GeneratedValue
    private Long id;

    @Column(nullable = true)
    @Getter
    @Setter
    private int matchId;

    @Column(nullable = true)
    @Getter
    @Setter
    private String Commentaire;

    public Commentaire() {

    }
    public Commentaire(String Commentaire, int matchId) {
        this.Commentaire = Commentaire;
        this.matchId = matchId;
    }
}
