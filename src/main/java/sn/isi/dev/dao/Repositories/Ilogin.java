package sn.isi.dev.dao.Repositories;

import sn.isi.dev.entities.Login;

public interface Ilogin {
	public int seConnecter(Login login);
	public void seDeconnecter();
}
