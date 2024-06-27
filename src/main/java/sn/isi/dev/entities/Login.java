package sn.isi.dev.entities;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;


public class Login {
	
	private String identifiant;
	private String motDePasse;
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	
}
