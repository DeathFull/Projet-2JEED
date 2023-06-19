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
@Table(name = "MatchEvenement")
public class Evenement {
    private static final long serialVersionUID = 1L;

    @Id
    @jakarta.persistence.GeneratedValue
    private Long id;

    @Column(nullable = true)
    @Getter
    @Setter
    private String type;

    @Column(nullable = true)
    @Getter
    @Setter
    private String player;

    @Column(nullable = true)
    @Getter
    @Setter
    private int gameTime;

    @Column(nullable = true)
    @Getter
    @Setter
    private int matchId;

    public Evenement() {

    }

    public Evenement(String type, String player, int gameTime, int matchId) {
        this.type = type;
        this.player = player;
        this.gameTime = gameTime;
        this.matchId = matchId;
    }
}
