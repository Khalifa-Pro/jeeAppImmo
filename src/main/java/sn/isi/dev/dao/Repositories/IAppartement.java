package sn.isi.dev.dao.Repositories;

import java.util.List;

import sn.isi.dev.entities.Appartement;
import sn.isi.dev.entities.Immeuble;


public interface IAppartement {
	public void ajouter(Appartement appartement);
	public List<Appartement> liste(String mc,long idImmeuble);
	public Appartement gestAppartementById(long id);
	public void modifier(Appartement appartement);
	public void archiver(Appartement appartement);


}
