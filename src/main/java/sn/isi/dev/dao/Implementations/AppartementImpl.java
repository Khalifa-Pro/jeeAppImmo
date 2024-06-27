package sn.isi.dev.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sn.isi.dev.dao.Repositories.IAppartement;
import sn.isi.dev.entities.Appartement;
import sn.isi.dev.entities.Immeuble;

public class AppartementImpl implements IAppartement {

	private SessionFactory sf;

	public AppartementImpl(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void ajouter(Appartement appartement) {
		// TODO Auto-generated method stub
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		System.out.println("APPARTEMENT: "+appartement);
		sn.save(appartement);
		tx.commit();
		sn.close();
	}

	@Override
	public List<Appartement> liste(String mc, long idImmeuble) {
	    List<Appartement> liste = new ArrayList<>();
	    Session sn = sf.openSession();
	    Transaction tx = sn.beginTransaction();
	    try {
	        if (mc != null) {
	            Query<Appartement> query = sn.createNamedQuery("Appartement.findByNumero", Appartement.class);
	            query.setParameter("idImmeuble", idImmeuble);
	            query.setParameter("numero_appt", mc);
	            query.setParameter("archiver", 0); 
	            liste = query.getResultList();
	        } else {
	            Query<Appartement> query = sn.createNamedQuery("Appartement.findAll", Appartement.class);
	            query.setParameter("idImmeuble", idImmeuble);
	            query.setParameter("archiver", 0); 
	            liste = query.getResultList();
	        }
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        sn.close();
	    }
	    return liste;
	}


	@Override
	public Appartement gestAppartementById(long id) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		try {
			Query<Appartement> queryById = sn.createNamedQuery("Appartement.findById", Appartement.class);
			queryById.setParameter("id", id);
			Appartement appartement = queryById.getSingleResult();
			tx.commit();
			return appartement;
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
	public void modifier(Appartement appartement) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		Query<Appartement> query = sn.createNamedQuery("Appartement.update");
		query.setParameter("numero_appt", appartement.getNumero_appt());
        query.setParameter("nombre_pieces", appartement.getNombre_pieces());
        query.setParameter("superficie", appartement.getSuperficie());
        query.setParameter("loyer", appartement.getLoyer());
        query.setParameter("id", appartement.getIdAppartement());
        query.executeUpdate();
		tx.commit();
		sn.close();
	}

	@Override
	public void archiver(Appartement appart) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		Query<Appartement> query = sn.createNamedQuery("Appartement.archiver");
		query.setParameter("archiver",1);
        query.setParameter("id", appart.getIdAppartement());
        query.executeUpdate();
		tx.commit();
		sn.close();
	}
}
