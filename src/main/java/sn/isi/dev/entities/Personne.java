package sn.isi.dev.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Personne implements Serializable {
	/**
	 * DEFAULT ID SERIALIZE
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private String metier;
	private SituationMatrimoniale situationMatrimoniale;
	private int nombreEnfant;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMetier() {
		return metier;
	}
	public void setMetier(String metier) {
		this.metier = metier;
	}
	public SituationMatrimoniale getSituationMatrimoniale() {
		return situationMatrimoniale;
	}
	public void setSituationMatrimoniale(SituationMatrimoniale situationMatrimoniale) {
		this.situationMatrimoniale = situationMatrimoniale;
	}
	public int getNombreEnfant() {
		return nombreEnfant;
	}
	public void setNombreEnfant(int nombreEnfant) {
		this.nombreEnfant = nombreEnfant;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
