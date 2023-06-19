package com.supinfo.leagueAppTdSpringSecurity.entities;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="journee")
public class Journee implements Serializable {
	
@Serial
private static final long serialVersionUID = 1L;
	
	@Id
	@jakarta.persistence.GeneratedValue
	private Long id;

	@Column(nullable = true)
	@Size(min=1,max=2)
	private String numJournee;
	
	public String getNumJournee() {
		return numJournee;
	}

	public void setNumJournee(String numJournee) {
		this.numJournee = numJournee;
	}

	public Saison getSaison() {
		return saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}

	@ManyToOne
	  // Déclaration d'une table d'association
	  @JoinTable(name = "saison_journee",
	             joinColumns = @JoinColumn(name = "journee_id"),
	             inverseJoinColumns = @JoinColumn(name = "saison_id"))
	private Saison saison;
	
	public Journee(@Size(min = 1, max = 2) String numJournee, Saison saison) {
		super();
		this.numJournee = numJournee;
		this.saison = saison;
	}

	public Journee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
