package sn.isi.dev.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import sn.isi.dev.dao.Implementations.AppartementImpl;
import sn.isi.dev.dao.Implementations.ImmeubleImpl;
import sn.isi.dev.dao.Repositories.IAppartement;
import sn.isi.dev.dao.Repositories.IImmeuble;
import sn.isi.dev.dao.connexion.SingletonConnection;
import sn.isi.dev.entities.Appartement;
import sn.isi.dev.entities.Immeuble;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.hibernate.SessionFactory;

/**
 * Servlet implementation class AppartementServlet
 */
public class AppartementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sf;
	private IAppartement iAppartement;
	private IImmeuble iImmeuble;
    /**
     * Default constructor. 
     */
    public AppartementServlet() {
        // Empty constructor
    }
    
    
    @Override
    public void init() throws ServletException {
        try {
        	sf = SingletonConnection.getSessionFactory();
        	this.iAppartement = new AppartementImpl(sf);
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			
			case "/ajouter-appartement":
				ajouter(request, response);
				break;
			case "/form-modifier-appartement":
				modificationForm(request, response);
				break;
			case "/modifier-appartement":
				modifier(request, response);
				break;
			case "/supprimer-appartement":
				supprimer(request, response);
				break;
			case "/appartement":
				lister(request, response);
				break;
			default:
				System.out.println("default");
				lister(request, response);
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
	
	
	/**
	 * FORMULAIRE D'AJOUT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
//	private void nouveauForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		long id = Long.parseLong(request.getParameter("id"));
//	    Immeuble immeuble = iImmeuble.gestImmeubleById(id); // Récupérez l'immeuble de votre DAO ou service
//	    request.setAttribute("immeuble", immeuble);
//	    request.getRequestDispatcher("views/appartement/creer.jsp").forward(request, response);
//
//	}
//	
	/**
	 * AJOUT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ajouter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numero_appt = request.getParameter("numero_appt");
        String nombre_pieces = request.getParameter("nombre_pieces");
        String superficie = request.getParameter("superficie");
        String idImmeuble = request.getParameter("idImmeuble");
        String loyer = request.getParameter("loyer");
        String archiver = request.getParameter("archiver");
        Part partImage = request.getPart("image");

        byte[] data = null;
        if (partImage != null && partImage.getSize() > 0) {
            try (InputStream is = partImage.getInputStream()) {
                data = is.readAllBytes();
            } catch (IOException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la lecture de l'image");
                return;
            }
        }

        Immeuble existImmo = iImmeuble.gestImmeubleById(Integer.parseInt(idImmeuble));

        Appartement appartement = new Appartement();
        appartement.setNumero_appt(numero_appt);
        appartement.setNombre_pieces(Integer.parseInt(nombre_pieces));
        appartement.setSuperficie(Double.parseDouble(superficie));
        appartement.setLoyer(Integer.parseInt(loyer));
        appartement.setArchiver(Integer.parseInt(archiver));
        appartement.setImmeuble(existImmo);
        appartement.setImage(data);

        try {
            iAppartement.ajouter(appartement);
            response.sendRedirect("appartement?id=" + idImmeuble); // Redirection vers la liste des appartements de l'immeuble
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'ajout de l'appartement");
        }
    }

	
	/**
	 * FORMULAIRE DE MODIFICATION 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modificationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
        Appartement existAppt = iAppartement.gestAppartementById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/immeuble/modifier.jsp");
        request.setAttribute("appartement", existAppt);
        dispatcher.forward(request, response);
	}
	
	/**
	 * MODIFICATION 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modifier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String numero_appt = request.getParameter("numero_appt");
		String nombre_pieces = request.getParameter("nombre_pieces");
		String superficie = request.getParameter("superficie");

		Appartement appartement = new Appartement();
		appartement.setIdAppartement(Long.parseLong(id));
		appartement.setNumero_appt(numero_appt);
		appartement.setNombre_pieces(Integer.parseInt(nombre_pieces));
		appartement.setSuperficie(Double.parseDouble(superficie));
		
		iAppartement.modifier(appartement);
		
		response.sendRedirect("appartement");
	}
	
	/**
	 * SUPPRESSION 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		Appartement existAppt = iAppartement.gestAppartementById(id);
		iAppartement.archiver(existAppt);
		response.sendRedirect("appartement");
	}
	
	/**
	 * LISTE 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void lister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    long id = Long.parseLong(request.getParameter("id"));
	    System.out.println("ID: " + id);
	    String motCle = request.getParameter("search");
	    List<Appartement> appartements = iAppartement.liste(motCle, id);
	    request.setAttribute("appartements", appartements);
	    request.getRequestDispatcher("views/appartement/lister.jsp").forward(request, response);
	}

}
