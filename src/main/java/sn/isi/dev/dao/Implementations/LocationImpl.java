package sn.isi.dev.dao.Implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sn.isi.dev.dao.Repositories.ILocation;
import sn.isi.dev.entities.Appartement;
import sn.isi.dev.entities.Location;

public class LocationImpl implements ILocation {
	
	private SessionFactory sf;

	public LocationImpl(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public void louer(Location location) {
		// TODO Auto-generated method stub
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		sn.save(location);
		tx.commit();
		sn.close();
		
	}

	@Override
	public List<Location> liste(String mc) {
		List<Location> liste = new ArrayList<Location>();
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		if (mc != null) {
			Query<Location> query = sn.createNamedQuery("Location.findByName", Location.class);
	        query.setParameter("nom", mc);
			liste = query.getResultList();
			sn.close();
			return liste;
		} else {
			Query<Location> query = sn.createNamedQuery("Location.findAll", Location.class);
			liste = query.getResultList();
			sn.close();
			return liste;
		}
	}

	@Override
	public Location gestLocationById(long id) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		try {
			Query<Location> queryById = sn.createNamedQuery("Location.findById", Location.class);
			queryById.setParameter("id", id);
			Location location = queryById.getSingleResult();
			tx.commit();
			return location;
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
	public void modifier(Location location) {
		Session sn = sf.openSession();
		Transaction tx = sn.beginTransaction();
		@SuppressWarnings({ "unchecked", "deprecation" })
		Query<Location> query = sn.createNamedQuery("Location.update");
        query.setParameter("id_appartement", location.getAppartement());
        query.setParameter("id_utilisateur", location.getUtilisateur());
        query.setParameter("mensualite", location.getMensualite());
        query.setParameter("updated_date", location.getUpdated_date());
        query.setParameter("idLocation", location.getIdLocation());
        query.executeUpdate();
		tx.commit();
		sn.close();
	}

	@Override
	public void supprimer(long id) {
		// TODO Auto-generated method stub
		
	}

}
