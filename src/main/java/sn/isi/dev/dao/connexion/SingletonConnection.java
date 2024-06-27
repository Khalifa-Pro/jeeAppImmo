package sn.isi.dev.dao.connexion;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import sn.isi.dev.entities.Appartement;
import sn.isi.dev.entities.Immeuble;
import sn.isi.dev.entities.Location;
import sn.isi.dev.entities.Utilisateur;



public class SingletonConnection {
	
	public static SessionFactory sf;
	
	/**
	 * Creation d'une session de connexion
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		
		if (sf == null) {
			
			Configuration configuration = new Configuration();
			
			Properties properties = new Properties();
			
			properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
			properties.put(Environment.URL,"jdbc:mysql://localhost:3306/app_immeuble_bd");
			properties.put(Environment.USER,"root");
			properties.put(Environment.PASS,"");
			properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL8Dialect");
			properties.put(Environment.HBM2DDL_AUTO,"update");
			properties.put(Environment.SHOW_SQL,true);
			
			configuration.setProperties(properties);
			configuration.addAnnotatedClass(Immeuble.class);
			configuration.addAnnotatedClass(Appartement.class);
			configuration.addAnnotatedClass(Utilisateur.class);
			configuration.addAnnotatedClass(Location.class);
			ServiceRegistry sr = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			
			sf = configuration.buildSessionFactory(sr);

		}
		return sf;
	}

}
