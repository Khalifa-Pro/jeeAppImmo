package sn.isi.dev.dao.Repositories;

import java.util.List;

import sn.isi.dev.entities.Login;
import sn.isi.dev.entities.Utilisateur;

public interface IUtilisateur {
	public void creer(Utilisateur user);
	public List<Utilisateur> liste(String mc);
	public Utilisateur gestUtilisateurById(long id);
	public void bloquer(Utilisateur user);
	public void debloquer(Utilisateur user);
	public Utilisateur findByLogin(Login login);
}
