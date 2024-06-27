package sn.isi.dev.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sn.isi.dev.dao.Repositories.IUtilisateur;
import sn.isi.dev.entities.Immeuble;
import sn.isi.dev.entities.Utilisateur;

public class UtilisateurImpl implements IUtilisateur {

	private SessionFactory sf;
	
	public UtilisateurImpl(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void creer(Utilisateur user) {
		// TODO Auto-generated method stub
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		sn.save(user);
		tx.commit();
		sn.close();
	}

	@Override
	public List<Utilisateur> liste(String mc) {
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		if (mc != null) {
			Query<Utilisateur> query = sn.createNamedQuery("Utilisateur.findByNom", Utilisateur.class);
	        query.setParameter("nom", mc);
			liste = query.getResultList();
			sn.close();
			return liste;
		} else {
			Query<Utilisateur> query = sn.createNamedQuery("Utilisateur.findAll", Utilisateur.class);
			liste = query.getResultList();
			sn.close();
			return liste;
		}
	}

	@Override
	public Utilisateur gestUtilisateurById(long id) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		try {
			Query<Utilisateur> queryById = sn.createNamedQuery("Utilisateur.findById", Utilisateur.class);
			queryById.setParameter("id", id);
			Utilisateur user = queryById.getSingleResult();
			tx.commit();
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			return null;
		}
		finally {
			sn.close();
		}
	}

	@Override
	public void bloquer(Utilisateur user) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		Query<Utilisateur> query = sn.createNamedQuery("Utilisateur.bloquer");
		query.setParameter("etat", user.getEtat());
        query.setParameter("id", user.getId());
        query.executeUpdate();
		tx.commit();
		sn.close();

	}

	@Override
	public void debloquer(Utilisateur user) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		Query<Utilisateur> query = sn.createNamedQuery("Utilisateur.bloquer");
		query.setParameter("etat", user.getEtat());
        query.setParameter("id", user.getId());
        query.executeUpdate();
		tx.commit();
		sn.close();
		
	}

}
