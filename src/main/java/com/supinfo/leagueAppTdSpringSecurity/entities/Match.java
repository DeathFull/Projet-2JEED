package com.supinfo.leagueAppTdSpringSecurity.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "matches")
public class Match implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @jakarta.persistence.GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Column(nullable = true)
    @Getter
    @Setter
    private Date date;

    @Size(min = 5, max = 60)
    @Column(nullable = true, length = 250)
    @Getter
    @Setter
    private String lieu;

    @Column(nullable = true, length = 250)
    @Getter
    @Setter
    private String statut;

    @Column(nullable = true, length = 250)
    @Getter
    @Setter
    private String score;

    public Match(Date date, String lieu) {
        super();
        this.date = date;
        this.lieu = lieu;
        this.statut = "waiting";
    }

    public Match() {
        super();
    }
}