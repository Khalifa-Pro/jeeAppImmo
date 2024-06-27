package sn.isi.dev.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sn.isi.dev.dao.Repositories.IImmeuble;
import sn.isi.dev.entities.Immeuble;

public class ImmeubleImpl implements IImmeuble {
	
	private SessionFactory sf;
	
	public ImmeubleImpl(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void ajouter(Immeuble immeuble) {
		// TODO Auto-generated method stub
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		sn.save(immeuble);
		tx.commit();
		sn.close();
		
	}

	@Override
	public List<Immeuble> liste(String mc) {
		List<Immeuble> liste = new ArrayList<Immeuble>();
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		if (mc != null) {
			Query<Immeuble> query = sn.createNamedQuery("Immeuble.findByName", Immeuble.class);
			query.setParameter("archiver", 0);
	        query.setParameter("nom", mc);
			liste = query.getResultList();
			sn.close();
			return liste;
		} else {
			Query<Immeuble> query = sn.createNamedQuery("Immeuble.findAll", Immeuble.class);
			query.setParameter("archiver", 0);
			liste = query.getResultList();
			sn.close();
			return liste;
		}
	}

	@Override
	public Immeuble gestImmeubleById(long id) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		try {
			Query<Immeuble> queryById = sn.createNamedQuery("Immeuble.findById", Immeuble.class);
			queryById.setParameter("id", id);
			Immeuble immeuble = queryById.getSingleResult();
			tx.commit();
			return immeuble;
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
	public void modifier(Immeuble immeuble) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		Query<Immeuble> query = sn.createNamedQuery("Immeuble.update");
		query.setParameter("nom", immeuble.getNom());
		query.setParameter("adresse", immeuble.getAdresse());
        query.setParameter("description", immeuble.getDescription());
        query.setParameter("id", immeuble.getIdImmeuble());
        query.executeUpdate();
		tx.commit();
		sn.close();
	}

	@Override
	public void archiver(Immeuble immeuble) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		Query<Immeuble> query = sn.createNamedQuery("Immeuble.archiver");
		query.setParameter("archiver",1);
        query.setParameter("id", immeuble.getIdImmeuble());
        query.executeUpdate();
		tx.commit();
		sn.close();
	}
	@Override
	public List<Immeuble> listeCombo() {
		List<Immeuble> liste = new ArrayList<Immeuble>();
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		
		Query<Immeuble> query = sn.createNamedQuery("Immeuble.findAllCombo", Immeuble.class);
		liste = query.getResultList();
		sn.close();
		return liste;
	}

}
