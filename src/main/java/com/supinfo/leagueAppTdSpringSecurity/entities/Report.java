package com.supinfo.leagueAppTdSpringSecurity.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Table(name = "MatchReport")
public class Report {
    private static final long serialVersionUID = 1L;

    @Id
    @jakarta.persistence.GeneratedValue
    private Long id;

    @Column(nullable = true)
    @Getter
    @Setter
    private Date lastDate;

    @Column(nullable = true)
    @Getter
    @Setter
    private Date newDate;

    @Column(nullable = true)
    @Getter
    @Setter
    private String reason;

    @Column(nullable = true)
    @Getter
    @Setter
    private int matchId;

    public Report() {

    }

    public Report(Date lastDate, Date newDate, String reason, int matchId) {
        this.lastDate = lastDate;
        this.newDate = newDate;
        this.reason = reason;
        this.matchId = matchId;
    }
}
