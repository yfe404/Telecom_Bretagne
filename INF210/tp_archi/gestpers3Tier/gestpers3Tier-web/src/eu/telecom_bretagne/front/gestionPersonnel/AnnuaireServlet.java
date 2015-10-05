package eu.telecom_bretagne.front.gestionPersonnel;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import eu.telecom_bretagne.data.model.Personne;

/**
 * Servlet implementation class AnnuaireServlet
 */
@WebServlet("/AnnuaireServlet")
public class AnnuaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnnuaireServlet() {
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
			annuaire(request.getParameter("tri"),
					request.getParameter("ordre"), out);
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
		} finally {
			out.close();
		}
	}

	/**
	 * Liste des personnes de LENTREPRISE
	 * 
	 * @param tri
	 *            définit le critère de tri de la liste
	 * @param ordre
	 *            définit l'ordre (ascendant,descendent)
	 * @param out
	 */
	protected void annuaire(String tri, String ordre, PrintWriter out) {
		// Par défaut la servlet rend une liste ordonnée (ascendant) par le
		// numéro de personne
		if (tri == null) {
			tri = "id";
			ordre = "asc";
		}

		// Page HTML affichant la liste des personnes
		affichePersonnes(out);

		// Obtention des personnes de la base de données
		List<Personne> lPersonnes = personnes(tri, ordre);
		if (lPersonnes != null) {
			for (Personne p : lPersonnes) {
				out.println(p.toHtml());
			}
		}

		out.println("    </table>");
		out.println("  </center><hr>");
		out.println("<a href=\"index.jsp\">Page principale <img src=\"images/return.gif\" alt=\"return\"></a>");
		out.println("</body>");
		out.println("</html>");
	}

	/*****************************************************
	 * Cette méthode s'occupe de l'affichage de la page HTML donnant la liste de
	 * toutes les personnes
	 ******************************************************/
	protected void affichePersonnes(PrintWriter out) {
		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>"
				+ "<h1>Annuaire de LENTREPRISE</h1>"
				+ "<center>"
				+ "<table border=\"1\">"
				+ "<th>Num_pers<br>"
				+ "<a href=\"/gestpers3Tier-web/AnnuaireServlet?tri=id&ordre=asc\">A</a> "
				+ "<a href=\"/gestpers3Tier-web/AnnuaireServlet?tri=id&ordre=desc\">D</a></th>"
				+ "<th>Nom<br>"
				+ "<a href=\"/gestpers3Tier-web/AnnuaireServlet?tri=nom&ordre=asc\">A</a> "
				+ "<a href=\"/gestpers3Tier-web/AnnuaireServlet?tri=nom&ordre=desc\">D</a></th>"
				+ "<th>Prénom<br>"
				+ "<a href=\"/gestpers3Tier-web/AnnuaireServlet?tri=prenom&ordre=asc\">A</a> "
				+ "<a href=\"/gestpers3Tier-web/AnnuaireServlet?tri=prenom&ordre=desc\">D</a></th>"
				+ "<th>Téléphone</th>" + "<th>Fonction</th>"
				+ "<th>Service<br>" + "</th>");
	}

	protected List<Personne> personnes(String tri, String ordre) {
		List<Personne> personnes = null;

		// Obtient la liste du personnel de LENTREPRISE
		// TODO Utilise l'EJB correspondant

		return personnes;
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