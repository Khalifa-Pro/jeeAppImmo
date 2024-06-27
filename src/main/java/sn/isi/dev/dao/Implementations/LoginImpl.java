package sn.isi.dev.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sn.isi.dev.dao.Repositories.Ilogin;
import sn.isi.dev.entities.Login;
import sn.isi.dev.entities.Profil;
import sn.isi.dev.entities.Utilisateur;

public class LoginImpl implements Ilogin {
	
	private SessionFactory sf;

	public LoginImpl(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	@Override
	public int seConnecter(Login login) {
	    Session sn = null;
	    try {
	        sn = sf.openSession();
	        
	        // Utiliser une requête paramétrée pour éviter l'injection SQL
	        Query<Utilisateur> query = sn.createQuery("FROM Utilisateur WHERE identifiant = :identifiant", Utilisateur.class);
	        query.setParameter("identifiant", login.getIdentifiant());
	        
	        Utilisateur utilisateur = query.uniqueResult();
	        
	        if (utilisateur == null) {
	            System.out.println("IDENTIFIANT OU MOT DE PASSE INCORRECT !");
	            return -2;
	        }
	        
	        // Vérifier le mot de passe (ici il faut utiliser un hachage)
	        if (!utilisateur.getMotDePasse().equals(login.getMotDePasse())) {
	            System.out.println("IDENTIFIANT OU MOT DE PASSE INCORRECT !");
	            return -2;
	        }
	        
	        if (utilisateur.getEtat() != 1) {
	            System.out.println("UTILISATEUR BLOQUE !");
	            return -1;
	        }
	        
	        if (utilisateur.getProfil().equals(Profil.ADMIN)) {
	            System.out.println("CONNEXION REUSSIE AVEC ADMIN !");
	            return 0;
	        } else if (utilisateur.getProfil().equals(Profil.PROPRIETAIRE)) {
	            System.out.println("CONNEXION REUSSIE AVEC PROPRIETAIRE !");
	            return 1;
	        } else if (utilisateur.getProfil().equals(Profil.LOCATAIRE)) {
	            System.out.println("CONNEXION REUSSIE AVEC LOCATAIRE !");
	            return 2;
	        } else {
	            System.out.println("PROFIL INCONNU !");
	            return -3;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -3;
	    } finally {
	        if (sn != null) {
	            sn.close();
	        }
	    }
	}


	@Override
	public void seDeconnecter() {
		// TODO Auto-generated method stub
		
	}

}
