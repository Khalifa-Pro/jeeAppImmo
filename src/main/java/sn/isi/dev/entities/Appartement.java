package sn.isi.dev.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appartement")
@NamedQueries({
	@NamedQuery(name = "Appartement.findAll", query = "SELECT a FROM Appartement a WHERE a.immeuble.idImmeuble = :idImmeuble AND a.archiver = :archiver"),
    @NamedQuery(name = "Appartement.findByNumero", query = "SELECT a FROM Appartement a WHERE a.numero_appt = :numero_appt AND a.immeuble.idImmeuble = :idImmeuble AND a.archiver = :archiver"),
    @NamedQuery(name = "Appartement.findById", query = "SELECT a FROM Appartement a WHERE a.id = :id"),
    @NamedQuery(name = "Appartement.update", query = "UPDATE Appartement a set a.numero_appt = :numero_appt,a.nombre_pieces = :nombre_pieces,a.superficie = :superficie,a.loyer = :loyer where id = :id"),
    @NamedQuery(name = "Appartement.delete", query = "DELETE FROM Appartement a WHERE a.id = :id"),

})
public class Appartement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAppartement;
	@Column(name = "numéro_Appartement", nullable = false, length = 15,unique = true)
	private String numero_appt;
	@Column(name = "nombre_pièces")
	private int nombre_pieces;
	@Column(name = "superficie")
	private double superficie;
	@Column(name = "loyer")
	private int loyer;
	private int archiver;
	@Lob
    @Column(name = "image", columnDefinition="LONGBLOB")
	private byte[] image;
	@ManyToOne
    @JoinColumn(name="immeuble_id", nullable=false)
    private Immeuble immeuble;
	
	@OneToOne(mappedBy = "appartement")
    private Location location;
	
	public Long getIdAppartement() {
		return idAppartement;
	}
	public int getArchiver() {
		return archiver;
	}
	public void setArchiver(int archiver) {
		this.archiver = archiver;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public void setIdAppartement(Long idAppartement) {
		this.idAppartement = idAppartement;
	}
	public String getNumero_appt() {
		return numero_appt;
	}
	public void setNumero_appt(String numero_appt) {
		this.numero_appt = numero_appt;
	}
	public int getNombre_pieces() {
		return nombre_pieces;
	}
	public void setNombre_pieces(int nombre_pieces) {
		this.nombre_pieces = nombre_pieces;
	}
	public double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	public int getLoyer() {
		return loyer;
	}
	public void setLoyer(int loyer) {
		this.loyer = loyer;
	}
	public Immeuble getImmeuble() {
		return immeuble;
	}
	public void setImmeuble(Immeuble immeuble) {
		this.immeuble = immeuble;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	

}
