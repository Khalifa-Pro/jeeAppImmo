package sn.isi.dev.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(
        name = "Location.findByName",
        query = "SELECT l FROM Location l JOIN l.utilisateur u WHERE u.nom = :nom"
    ),
    @NamedQuery(name = "Location.findById", query = "SELECT l FROM Location l WHERE l.id = :id"),
    @NamedQuery(
        name = "Location.update",
        query = "UPDATE Location l SET l.mensualite = :mensualite, l.appartement.idAppartement = :id_appartement, l.utilisateur.id = :id_utilisateur WHERE l.idLocation = :idLocation"
    ),
    @NamedQuery(name = "Location.delete", query = "DELETE FROM Location l WHERE l.id = :id"),
})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLocation")
    private Long idLocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id")
    private Utilisateur utilisateur;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_appartement", referencedColumnName = "idAppartement")
    private Appartement appartement;

    @Column(name = "mensualité")
    private int mensualite;

    @Column(name = "date_de_creation")
    @Temporal(TemporalType.DATE)
    private Date created_date;

    @Column(name = "date_de_modification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_date;

	public Long getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Appartement getAppartement() {
		return appartement;
	}

	public void setAppartement(Appartement appartement) {
		this.appartement = appartement;
	}

	public int getMensualite() {
		return mensualite;
	}

	public void setMensualite(int mensualite) {
		this.mensualite = mensualite;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

    // Getters and Setters
    
}

