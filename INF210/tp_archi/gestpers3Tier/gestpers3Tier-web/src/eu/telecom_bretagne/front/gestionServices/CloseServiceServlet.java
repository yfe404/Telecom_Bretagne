package eu.telecom_bretagne.front.gestionServices;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CloseServiceServlet
 */
@WebServlet("/CloseServiceServlet")
public class CloseServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CloseServiceServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			closeService(request.getParameter("nom"), out);
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
		} finally {
			out.close();
		}
	}

    /**
     * Méthode pour fermer un service de LENTREPRISE
     */
    protected void closeService(String nom,PrintWriter out) {

        afficheCloseService(nom, out);

        // Supprime le service
        // TODO Appel l'EJB correspondant pour le faire
		
        out.println("</table>");
        out.println("<center><h3> Effectué</h3></center>");
        out.println("<hr>");
        out.println("<a href=\"index.jsp\">Page principale <img src=\"images/return.gif\" alt=\"return\"></a>");
        out.println("</body>");
        out.println("</html>");

    }

    protected void afficheCloseService(String nom, PrintWriter out) {

        out.println("<html>"
                + "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
                + "<body>"
                + "<h1>Fermeture d'un service :</h1>"
                + "<table border=\"1\">"
                + "<tr><b>Nom</b> : " + nom + "<br>");
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
