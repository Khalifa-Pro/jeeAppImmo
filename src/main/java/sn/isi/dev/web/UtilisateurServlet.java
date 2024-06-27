package sn.isi.dev.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.isi.dev.dao.Implementations.ImmeubleImpl;
import sn.isi.dev.dao.Implementations.LoginImpl;
import sn.isi.dev.dao.Implementations.UtilisateurImpl;
import sn.isi.dev.dao.Repositories.IImmeuble;
import sn.isi.dev.dao.Repositories.IUtilisateur;
import sn.isi.dev.dao.Repositories.Ilogin;
import sn.isi.dev.dao.connexion.SingletonConnection;
import sn.isi.dev.entities.Immeuble;
import sn.isi.dev.entities.Login;
import sn.isi.dev.entities.Profil;
import sn.isi.dev.entities.Utilisateur;

import java.io.IOException;
import java.util.List;

import org.hibernate.SessionFactory;

/**
 * Servlet implementation class UtilisateurServlet
 */
public class UtilisateurServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SessionFactory sf;
    private IUtilisateur iUtilisateur;
    private Ilogin ilogin;
    private IImmeuble iImmeuble;

    /**
     * Default constructor.
     */
    public UtilisateurServlet() {
        // Empty constructor
    }

    @Override
    public void init() throws ServletException {
        try {
            sf = SingletonConnection.getSessionFactory();
            this.iUtilisateur = new UtilisateurImpl(sf);
            this.ilogin = new LoginImpl(sf);
            this.iImmeuble = new ImmeubleImpl(sf);
        } catch (Exception e) {
            throw new ServletException("Error initializing SessionFactory", e);
        }
    }

    @Override
    public void destroy() {
        if (sf != null) {
            sf.close();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/deconnexion":
                    deconnecter(request, response);
                    break;
                case "/inscription":
                    pageInscription(request, response);
                    break;
                case "/": // formulaire-connexion-prop
                    pageConnexionAdmin(request, response);
                    break;
                case "/$2y$10$ylwWqxQEliS32IDhne9d5.PsUa5dKI0H9bgR72xGaLPKLU.anrq.K": // formulaire-connexion-prop
                    pageConnexionProp(request, response);
                    break;
                case "/$2y$10$RWIz9TZyxs5eM7K4lENte.9RrUBkBOjnrTnbMGWMstJrkKw7zsO": // formulaire-connexion-loc
                    pageConnexionLoc(request, response);
                    break;
                case "/admin":
                    redirectAdmin(request, response);
                    break;
                case "/prop":
                    redirectProp(request, response);
                    break;
                case "/loc":
                    redirectLoc(request, response);
                    break;
                default:
                	lister(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/inscription":
                    inscrire(request, response);
                    break;
                case "/inscriptionProp":
                    inscrireProp(request, response);
                    break;
                case "/inscriptionLoc":
                    inscrireLoc(request, response);
                    break;
                case "/connexion":
                    connecter(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page non trouvée");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur");
        }
    }
    
    ///-------------------------------------------------------------------------
    private void redirectAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/immeuble/lister.jsp").forward(request, response);
    }
    
    private void redirectProp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/utilisateur/proprietaire/listerImmo.jsp").forward(request, response);
    }
    
    private void redirectLoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String motCle = request.getParameter("search");
		List<Immeuble> immeubles = iImmeuble.liste(motCle);
		request.setAttribute("immeubles", immeubles);
		request.getRequestDispatcher("views/utilisateur/locataire/accueil.jsp").forward(request, response);

    }
    //--------------------------------------------------------------------------

    private void pageConnexionAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/utilisateur/auth/forms.jsp").forward(request, response);
    }

    private void pageConnexionProp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/utilisateur/auth/formsProp.jsp").forward(request, response);
    }

    private void pageConnexionLoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/utilisateur/auth/formsLoc.jsp").forward(request, response);
    }

    private void connecter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Login newLog = new Login();
        newLog.setIdentifiant(username);
        newLog.setMotDePasse(password);
        
        int profil = ilogin.seConnecter(newLog);
        if (profil != -1) { // Assuming -1 means invalid credentials
            Utilisateur utilisateur = iUtilisateur.findByLogin(newLog); // Assuming this method exists to get user by login
            
            // Store user information in session
            request.getSession().setAttribute("utilisateur", utilisateur);
            
            if (profil == 0) { // Admin
                response.sendRedirect(request.getContextPath() + "/admin");
            } else if (profil == 1) { // Propriétaire
                response.sendRedirect(request.getContextPath() + "/prop");
            } else if (profil == 2) { // Locataire
                response.sendRedirect(request.getContextPath() + "/loc");
            }
        } else {
            request.setAttribute("message", "Invalid username or password");
            pageInscription(request, response);
        }
    }


    
    private void inscrire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //String profil = request.getParameter("profil");
        String etat = request.getParameter("etat");

        Utilisateur user = new Utilisateur();
        user.setNom(lastName);
        user.setPrenom(firstName);
        user.setIdentifiant(username);
        user.setMotDePasse(password);
        user.setProfil(Profil.ADMIN);
        user.setEtat(Integer.parseInt(etat));

        try {
            iUtilisateur.creer(user);
            request.setAttribute("message", "Inscription réussie");
            pageInscription(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'ajout de l'utilisateur");
        }
    }
    
    private void inscrireProp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //String profil = request.getParameter("profil");
        String etat = request.getParameter("etat");

        Utilisateur user = new Utilisateur();
        user.setNom(lastName);
        user.setPrenom(firstName);
        user.setIdentifiant(username);
        user.setMotDePasse(password);
        user.setProfil(Profil.PROPRIETAIRE);
        user.setEtat(Integer.parseInt(etat));

        try {
            iUtilisateur.creer(user);
            request.setAttribute("message", "Inscription réussie");
            pageInscription(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'ajout de l'utilisateur");
        }
    }
    
    private void inscrireLoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //String profil = request.getParameter("profil");
        String etat = request.getParameter("etat");

        Utilisateur user = new Utilisateur();
        user.setNom(lastName);
        user.setPrenom(firstName);
        user.setIdentifiant(username);
        user.setMotDePasse(password);
        user.setProfil(Profil.LOCATAIRE);
        user.setEtat(Integer.parseInt(etat));

        try {
            iUtilisateur.creer(user);
            request.setAttribute("message", "Inscription réussie");
            pageInscription(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'ajout de l'utilisateur");
        }
    }

    private void deconnecter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logique de déconnexion
    }

    private void pageInscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/utilisateur/auth/forms.jsp").forward(request, response);
    }
    
    private void lister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String motCle = request.getParameter("search");
		List<Immeuble> immeubles = iImmeuble.liste(motCle);
		request.setAttribute("immeubles", immeubles);
		request.getRequestDispatcher("views/immeuble/lister.jsp").forward(request, response);
		
	}
}
