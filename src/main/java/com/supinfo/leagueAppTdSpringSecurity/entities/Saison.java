package com.supinfo.leagueAppTdSpringSecurity.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "saison")
public class Saison implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @jakarta.persistence.GeneratedValue
    private Long id;
    @Column(nullable = true)
    @Size(min = 5, max = 60)
    @Getter
    @Setter
    private String libelle;
    @Column(nullable = true)
    @NotEmpty
    @Size(min = 5, max = 20)
    @Getter
    @Setter
    private String identifiantSaison;

    public Saison() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Saison(@Size(min = 5, max = 60) String libelle,
                  @NotEmpty @Size(min = 5, max = 20) String identifiantSaison) {
        super();
        this.libelle = libelle;
        this.identifiantSaison = identifiantSaison;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
