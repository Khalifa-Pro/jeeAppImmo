package sn.isi.dev.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "immeuble")
@NamedQueries({
    @NamedQuery(name = "Immeuble.findAll", query = "SELECT i FROM Immeuble i WHERE i.archiver =: archiver"),
    @NamedQuery(name = "Immeuble.findAllCombo", query = "SELECT i FROM Immeuble i"),
    @NamedQuery(name = "Immeuble.findByName", query = "SELECT i FROM Immeuble i WHERE i.nom = :nom AND i.archiver =: archiver"),
    @NamedQuery(name = "Immeuble.findById", query = "SELECT i FROM Immeuble i WHERE i.id = :id"),
    @NamedQuery(name = "Immeuble.update", query = "UPDATE Immeuble i set i.nom = :nom, i.adresse = :adresse, i.description = :description where id = :id"),
    @NamedQuery(name = "Immeuble.archiver", query = "UPDATE Immeuble i set i.archiver = :archiver where id = :id"),


})
public class Immeuble {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idImmeuble;
	@Column(name = "nom_immeuble")
	private String nom;
	@Column(name = "adresse")
	private String adresse;
	@Column(name = "description")
	private String description;
	private int archiver;
	@OneToMany(mappedBy="immeuble")
	private Set<Appartement> appartements;
	@Lob
    @Column(name = "image", columnDefinition="LONGBLOB")
	private byte[] image;
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Long getIdImmeuble() {
		return idImmeuble;
	}
	public void setIdImmeuble(Long idImmeuble) {
		this.idImmeuble = idImmeuble;
	}
	public String getNom() {
		return nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Appartement> getAppartements() {
		return appartements;
	}
	public void setAppartements(Set<Appartement> appartements) {
		this.appartements = appartements;
	}
	public int getArchiver() {
		return archiver;
	}
	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}
	

}
