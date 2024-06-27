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
 * Servlet implementation class ImmeubleServlet
 */
public class ImmeubleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sf;
	private IImmeuble iImmeuble;
	private IAppartement iAppartement;
    /**
     * Default constructor. 
     */
    public ImmeubleServlet() {
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
		//request.getRequestDispatcher("views/liste.jsp").forward(request, response);
		String action = request.getServletPath();
		
		try {
			switch (action) {
			
			case "/nouveau-immeuble":
				nouveauForm(request, response);
				break;
			case "/ajouter-immeuble":
				ajouter(request, response);
				break;
			case "/ajouter-appartement":
				ajouterApp(request, response);
				break;
			case "/form-modifier-immeuble":
				modificationForm(request, response);
				break;
			case "/modifier-immeuble":
				modifier(request, response);
				break;
			case "/supprimer-immeuble":
				supprimer(request, response);
				break;
			case "/nouveau-appartement":
                nouveauFormApp(request, response);
                break;
			default:
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
	 * FORMULAIRE D'AJOUT DE NOUVEAU PRODUIT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void nouveauForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("views/immeuble/creer.jsp");
	}
	
	/**
	 * FORMULAIRE D'AJOUT DE NOUVEL APPARTEMENT DANS UN IMMEUBLE EXISTANT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void nouveauFormApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    long idImmeuble = Long.parseLong(request.getParameter("id"));
	    Immeuble immeuble = iImmeuble.gestImmeubleById(idImmeuble);
	    
	    if (immeuble == null) {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Immeuble non trouvé avec l'ID : " + idImmeuble);
	        return;
	    }
	    
	    request.setAttribute("immeuble", immeuble);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("views/appartement/creer.jsp");
	    dispatcher.forward(request, response);
	}
	
	/**
	 * AJOUT D'UN PRODUIT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ajouter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nom = request.getParameter("nom");
	    String adresse = request.getParameter("adresse");
	    String description = request.getParameter("description");
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

	    Immeuble immeuble = new Immeuble();
	    immeuble.setNom(nom);
	    immeuble.setAdresse(adresse);
	    immeuble.setDescription(description);
	    immeuble.setImage(data);

	    try {
	        iImmeuble.ajouter(immeuble);
	        response.sendRedirect("immeuble");
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'ajout de l'immeuble");
	    }
	}
	
	private void ajouterApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	 * FORMULAIRE DE MODIFICATION DE NOUVEAU PRODUIT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modificationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
        Immeuble existImmeuble = iImmeuble.gestImmeubleById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/immeuble/modifier.jsp");
        request.setAttribute("immeuble", existImmeuble);
        dispatcher.forward(request, response);
	}
	
	/**
	 * MODIFICATION D'UN PRODUIT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modifier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String nom = request.getParameter("nom");
		String adresse = request.getParameter("adresse");
		String description = request.getParameter("description");

		Immeuble immeuble = new Immeuble();
		immeuble.setIdImmeuble(Long.parseLong(id));
		immeuble.setNom(nom);
		immeuble.setAdresse(adresse);
		immeuble.setDescription(description);
		
		iImmeuble.modifier(immeuble);
		
		response.sendRedirect("immeuble");
	}
	
	/**
	 * SUPPRESSION D'UN PRODUIT
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		Immeuble existImmo = iImmeuble.gestImmeubleById(id);
		iImmeuble.archiver(existImmo);
		response.sendRedirect("immeuble");
	}
	
	/**
	 * LISTE DES PRODUITS
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void lister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String motCle = request.getParameter("search");
		List<Immeuble> immeubles = iImmeuble.liste(motCle);
		request.setAttribute("immeubles", immeubles);
		request.getRequestDispatcher("views/immeuble/lister.jsp").forward(request, response);
		
	}
	
//	private void listerAppartements(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    long id = Long.parseLong(request.getParameter("id"));
//	    String motCle = request.getParameter("search");
//	    List<Appartement> appartements = iAppartement.liste(motCle, id);
//	    System.out.println("Nombre d'appartements trouvés: " + appartements.size());  // Debug line
//	    request.setAttribute("appartements", appartements);
//	    request.getRequestDispatcher("views/appartement/lister.jsp").forward(request, response);
//	}


}
