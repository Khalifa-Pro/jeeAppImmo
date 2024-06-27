package sn.isi.dev.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.isi.dev.dao.Implementations.AppartementImpl;
import sn.isi.dev.dao.Implementations.ImmeubleImpl;
import sn.isi.dev.dao.Implementations.LoginImpl;
import sn.isi.dev.dao.Implementations.UtilisateurImpl;
import sn.isi.dev.dao.Repositories.IAppartement;
import sn.isi.dev.dao.Repositories.IImmeuble;
import sn.isi.dev.dao.Repositories.IUtilisateur;
import sn.isi.dev.dao.Repositories.Ilogin;
import sn.isi.dev.dao.connexion.SingletonConnection;
import sn.isi.dev.entities.Appartement;
import sn.isi.dev.entities.Immeuble;

import java.io.IOException;
import java.util.List;

import org.hibernate.SessionFactory;

/**
 * Servlet implementation class LocataireServlet
 */
public class LocataireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sf;
    private IImmeuble iImmeuble;
    private IAppartement iAppartement;

    /**
     * Default constructor.
     */
    public LocataireServlet() {
        // Empty constructor
    }

    @Override
    public void init() throws ServletException {
        try {
            sf = SingletonConnection.getSessionFactory();
            this.iImmeuble = new ImmeubleImpl(sf);
            this.iAppartement = new AppartementImpl(sf);
        } catch (Exception e) {
            throw new ServletException("Error initializing SessionFactory", e);
        }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		try {
			switch (action) {
			case "/louer":
                louer(request, response);
                break;
			case "/appartement-dispo":
                listerApp(request, response);
                break;
			default:
				espaceLocataire(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void louer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
	
	private void espaceLocataire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String motCle = request.getParameter("search");
		List<Immeuble> immeubles = iImmeuble.liste(motCle);
		request.setAttribute("immeubles", immeubles);
		request.getRequestDispatcher("views/utilisateur/locataire/accueil.jsp").forward(request, response);

	}
	
	private void listerApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    long id = Long.parseLong(request.getParameter("id"));
	    System.out.println("ID: " + id);
	    String motCle = request.getParameter("search");
	    List<Appartement> appartements = iAppartement.liste(motCle, id);
	    request.setAttribute("appartements", appartements);
	    request.getRequestDispatcher("views/utilisateur/locataire/appartDispo.jsp").forward(request, response);
	}

}
