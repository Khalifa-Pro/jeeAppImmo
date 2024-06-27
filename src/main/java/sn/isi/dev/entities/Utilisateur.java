package sn.isi.dev.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findById", query = "SELECT u FROM Utilisateur u WHERE u.id = :id"),
    @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom"),
    @NamedQuery(name = "Utilisateur.bloquer", query = "UPDATE Utilisateur u set u.etat = :etat where u.id =: id"),
    @NamedQuery(name = "Utilisateur.debloquer", query = "UPDATE Utilisateur u set u.etat = :etat"),
    @NamedQuery(name = "Utilisateur.findByLogin", query = "SELECT u FROM Utilisateur u WHERE u.identifiant = :identifiant AND u.motDePasse = :motDePasse")

})
public class Utilisateur extends Personne {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(unique = true)
	private String identifiant;
	@Column(name = "mot_de_passe")
	private String motDePasse;
	private int etat;
	private Profil profil;
	
	@OneToOne(mappedBy = "utilisateur")
    private Location location;
	
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
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public Profil getProfil() {
		return profil;
	}
	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	
}
