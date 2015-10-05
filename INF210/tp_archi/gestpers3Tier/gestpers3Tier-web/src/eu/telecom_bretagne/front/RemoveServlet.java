package eu.telecom_bretagne.front;

import java.io.*;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import eu.telecom_bretagne.data.model.Service;
import eu.telecom_bretagne.services.IServicesBeanLocal;

/**
 * Servlet implementation class RemoveServlet
 */
@WebServlet("/RemoveServlet")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@EJB
	IServicesBeanLocal sBean;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveServlet() {
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
			// demande de licenciement d'une personne
			if (request.getParameter("type").equals("personne")) {
				// TBD afficheLicenciement(out);
			} else // demande fermeture d'un service
			{
				afficheCloseService(out);
			}
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
		} finally {
			out.close();
		}
	}

	/**
	 * Affichage du formulaire de licenciement d'une personne
	 */
	protected void afficheLicenciement(PrintWriter out) {
		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>" + "<h1>Licenciement<h1>"
				+ "<form action=\"/gestpers3Tier-web/LicenciementServlet\">"
				+ "<center><table>" + "<tr><td>Nom</td><td>"
				+ "<input type=\"text\" name=\"nom\">"
				+ "<tr><td>Prénom</td><td>"
				+ "<input type=\"text\" name=\"prenom\">"
				+ "<tr><td>Téléphone</td><td>"
				+ "<input type=\"text\" name=\"telephone\">"
				+ "<tr><td>Fonction</td><td>"
				+ "<input type=\"text\" name=\"fonction\">"
				+ "<tr><td>Service</td><td>" + "<select name=\"service\">");

		// Partie dynamique de la page
		for (Service s : services("id", "asc")) // Infos sur les services
		{
			out.println("<option value=\"" + s.getId() + "\">" + s.getNom()
					+ "</option>");
		}

		out.println("</select></table><br>"
				+ "<input type=\"submit\" value=\"OK\">" + "</form></center>"
				+ "</body></html>");
	}

	/**
	 * Affichage du formulaire de fermeture d'un service
	 */
	protected void afficheCloseService(PrintWriter out) {
		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>" + "<h1>Service à fermer<h1>"
				+ "<form action=\"/gestpers3Tier-web/CloseServiceServlet\">"
				+ "<center><table>" + "<tr><td>Nom</td><td>"
				+ "<input type=\"text\" name=\"nom\"></td></tr>");

		out.println("</select></table><br>"
				+ "<input type=\"submit\" value=\"OK\">" + "</form></center>"
				+ "</body></html>");
	}


	/**
	 * Liste des services de LENTREPRISE
	 * 
	 * @param tri
	 *            définit le critère de tri de la liste
	 * @param ordre
	 *            définit l'ordre (ascendant,descendent)
	 */
	protected List<Service> services(String tri, String ordre) {

		return sBean.listeServices(tri, ordre);
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