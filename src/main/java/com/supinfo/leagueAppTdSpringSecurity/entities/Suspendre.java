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
@Table(name = "Suspendre")
public class Suspendre {
    private static final long serialVersionUID = 1L;

    @Id
    @jakarta.persistence.GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Column(nullable = true)
    @Getter
    @Setter
    private String raison;

    @Column(nullable = true)
    @Getter
    @Setter
    private String statut;

    public Suspendre() {

    }

    public Suspendre(String raison, String statut) {
        this.raison = raison;
        this.statut = statut;
    }
}
