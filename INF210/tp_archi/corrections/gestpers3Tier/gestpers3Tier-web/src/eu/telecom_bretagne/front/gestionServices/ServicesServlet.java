package eu.telecom_bretagne.front.gestionServices;

import java.io.*;
import java.util.logging.*;
import java.util.*;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import eu.telecom_bretagne.data.model.Service;
import eu.telecom_bretagne.services.IServicesBeanLocal;

/**
 * Servlet implementation class ServicesServlet
 */
@WebServlet("/ServicesServlet")
public class ServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IServicesBeanLocal sBean;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServicesServlet() {
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
			listeServices(request.getParameter("tri"),
					request.getParameter("ordre"), out);
			out.close();
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
			out.close();
		} finally {
			out.close();
		}
	}

	/**
	 * Liste les services de LENTREPRISE
	 * 
	 * @param tri
	 *            définit le critère de tri de la liste
	 * @param ordre
	 *            définit l'ordre (ascendant,descendent)
	 * @param out
	 * @throws java.sql.SQLException
	 * @throws javax.naming.NamingException
	 */
	protected void listeServices(String tri, String ordre, PrintWriter out) {

		// Par défaut la servlet rend les services par ordre ascendant leur
		// numéro
		if (tri == null) {
			tri = "id";
			ordre = "asc";
		}

		// Tableau d'affichage des services
		afficheServices(out);

		// Obtention des services de la base de données
		for (Service s : services(tri, ordre)) {
			out.println(s.toHtml());
		}

		out.println("</table>");
		out.println("</center><hr>");
		out.println("<a href=\"index.jsp\">Page principale <img src=\"images/return.gif\" alt=\"return\"></a>");
		out.println("</body>");
		out.println("</html>");
	}

	/*****************************************************
	 * Cette méthode s'occupe de l'affichage de la partie statique de la page
	 * HTML donnant la liste de tous les services
	 * 
	 * @params out
	 ******************************************************/
	protected void afficheServices(PrintWriter out) {
		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>"
				+ "<h1>Services de LENTREPRISE</h1>"
				+ "<center>"
				+ "<table border=\"1\">"
				+ "<th>Num_service<br>"
				+ "<a href=\"/gestpers3Tier-web/ServicesServlet?tri=id&ordre=asc\">A</a> "
				+ "<a href=\"/gestpers3Tier-web/ServicesServlet?tri=id&ordre=desc\">D</a></th>"
				+ "<th>Nom<br>"
				+ "<a href=\"/gestpers3Tier-web/ServicesServlet?tri=nom&ordre=asc\">A</a> "
				+ "<a href=\"/gestpers3Tier-web/ServicesServlet?tri=nom&ordre=desc\">D</a></th>");
	}

	/*****************************************************
	 * Cette méthode rend la liste des services de LENTREPRISE
	 * 
	 * @param tri
	 * @param ordre
	 * @throws SQLException
	 ******************************************************/
	protected List<Service> services(String tri, String ordre) {
		List<Service> services = null;

		try {

			// Appel à la méthode de l'EJB correspondant
			services = sBean.listeServices(tri, ordre);

		} catch (Exception ex) {
			Logger.getLogger(ServicesServlet.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return services;
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