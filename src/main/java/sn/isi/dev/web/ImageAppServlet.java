package sn.isi.dev.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.isi.dev.dao.connexion.SingletonConnection;
import sn.isi.dev.entities.Appartement;
import sn.isi.dev.entities.Immeuble;

import java.io.IOException;
import java.io.OutputStream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Servlet implementation class ImageAppServlet
 */
public class ImageAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        try {
            sessionFactory = SingletonConnection.getSessionFactory();
        } catch (Exception e) {
            throw new ServletException("Error initializing SessionFactory", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Session session = sessionFactory.openSession();
        Appartement appartement = session.get(Appartement.class, id);
        session.close();

        if (appartement != null && appartement.getImage() != null) {
            response.setContentType("image/jpeg");
            OutputStream os = response.getOutputStream();
            os.write(appartement.getImage());
            os.flush();
            os.close();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
        }
    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
