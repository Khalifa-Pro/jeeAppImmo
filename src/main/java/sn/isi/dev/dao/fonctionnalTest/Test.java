package sn.isi.dev.dao.fonctionnalTest;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import sn.isi.dev.dao.Implementations.AppartementImpl;
import sn.isi.dev.dao.Implementations.ImmeubleImpl;
import sn.isi.dev.dao.Implementations.LocationImpl;
import sn.isi.dev.dao.Implementations.LoginImpl;
import sn.isi.dev.dao.Implementations.UtilisateurImpl;
import sn.isi.dev.dao.connexion.SingletonConnection;
import sn.isi.dev.entities.Appartement;
import sn.isi.dev.entities.Immeuble;
import sn.isi.dev.entities.Location;
import sn.isi.dev.entities.Login;
import sn.isi.dev.entities.Profil;
import sn.isi.dev.entities.Utilisateur;

public class Test {
	public static void main(String[] args) {
		// Initialisation de la session de connexion Hibernate
        SessionFactory sessionFactory = SingletonConnection.getSessionFactory();

        // Vérifier la création de la table en ouvrant et fermant une session
        sessionFactory.openSession().close();
        
        ImmeubleImpl daoImmeuble = new ImmeubleImpl(sessionFactory);
        
//        Immeuble immeuble = new Immeuble();
//        immeuble.setNom("ZEYNA");
//        immeuble.setAdresse("Hann-Mariste");
//        immeuble.setDescription("Un immeuble composant 4 étages dont chacun 4 appartements");
//        immeuble.setArchiver(0);
//        
//        daoImmeuble.ajouter(immeuble);
          AppartementImpl daoAppart = new AppartementImpl(sessionFactory);
//        Appartement appartement = new Appartement();
//        appartement.setNumero_appt("AZ01");
//        appartement.setNombre_pieces(3);
//        appartement.setSuperficie(100.00);
//        appartement.setLoyer(0);
//        Immeuble immeubleId = daoImmeuble.gestImmeubleById(1);
//        appartement.setImmeuble(immeubleId);
//        daoAppart.ajouter(appartement);
//        List<Immeuble> liste = daoImmeuble.liste("ZEYNA");
//        for (Immeuble immeuble : liste) {
//            System.out.println("LISTE: "+immeuble.getAdresse());
//
//		}
//        Immeuble immoModif = daoImmeuble.gestImmeubleById(1);
//        immoModif.setNom("Immeuble ZEYNA");
//        daoImmeuble.modifier(immoModif);
        //daoImmeuble.archiver(immoModif);
        // Fermer la sessionFactory
//        List<Appartement> liste = daoAppart.liste("AZ01");
//       	System.out.println("LISTE: "+liste);
//        Appartement appModif = daoAppart.gestAppartementById(1);
//        appModif.setLoyer(1);
//        daoAppart.modifier(appModif);
        //daoAppart.supprimer(1);
          
          
        UtilisateurImpl daoUser = new UtilisateurImpl(sessionFactory);
        
//        // Creer utilisateur
//        Utilisateur user = new Utilisateur();
//        user.setNom("SY");
//        user.setPrenom("Omar");
//        user.setIdentifiant("syomar1@gmail.com");
//        user.setMotDePasse("passer");
//        user.setProfil(Profil.LOCATAIRE);
//        user.setEtat(1);
//        daoUser.creer(user);
////        
//        Utilisateur user1 = new Utilisateur();
//        user.setNom("LY");
//        user.setPrenom("Abdoulaye");
//        user.setIdentifiant("lyabdoulaye@gmail.com");
//        user.setMotDePasse("passer");
//        user.setProfil(Profil.PROPRIETAIRE);
//        user.setEtat(1);
//        daoUser.creer(user);
        
//        Utilisateur user2 = new Utilisateur();
//        user2.setNom("ADMIN");
//        user2.setPrenom("Admin");
//        user2.setIdentifiant("admin@gmail.com");
//        user2.setMotDePasse("passer");
//        user2.setProfil(Profil.ADMIN);
//        user2.setEtat(1);
//        daoUser.creer(user2);
        
//        List<Utilisateur> liste = daoUser.liste("DIADHIOU");
//        System.out.println("LISTE :"+liste );
//        
//        Utilisateur userModif = daoUser.gestUtilisateurById(1);
//        userModif.setEtat(1);
//        daoUser.bloquer(userModif);
//        
//        LoginImpl daoLog = new LoginImpl(sessionFactory);
//        Login login = new Login();
//        login.setIdentifiant("lyabdoulaye@gmail.com");
//        login.setMotDePasse("passer");
//        daoLog.seConnecter(login);
        
        LocationImpl daoLocation = new LocationImpl(sessionFactory);
        
//        Appartement getAp = daoAppart.gestAppartementById(1);
//        
//        Utilisateur getUser = daoUser.gestUtilisateurById(1);
////        
//        Date dateActu = new Date();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        //String date = simpleDateFormat.format(dateActu);
        //System.out.println("LA DATE: "+date);
//        if (getAp.getLoyer() == 1) {
//			System.out.println("Appartement déjà loué!");
//		}
//        else {
//        	Location loc1 = new Location();
//            loc1.setAppartement(getAp);
//            loc1.setUtilisateur(getUser);
//            loc1.setMensualite(150000);
//            loc1.setCreated_date(dateActu);
//            
//            daoLocation.louer(loc1);
//            
//            System.out.println("SUCCES!");
//        }
        
//        List<Location> liste = daoLocation.liste("SY");
//        System.out.println("LISTE_AVEC_NOM: "+liste);

        sessionFactory.close();
	}
}
