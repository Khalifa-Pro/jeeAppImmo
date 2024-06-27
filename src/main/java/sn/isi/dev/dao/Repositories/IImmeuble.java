package sn.isi.dev.dao.Repositories;

import java.util.List;

import sn.isi.dev.entities.Immeuble;

public interface IImmeuble {
	public void ajouter(Immeuble immeuble);
	public List<Immeuble> liste(String mc);
	public List<Immeuble> listeCombo();
	public Immeuble gestImmeubleById(long id);
	public void modifier(Immeuble immeuble);
	public void archiver(Immeuble immeuble);

}
