package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.telecom_bretagne.cabinet_recrutement.front.utils.AssetsLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature;

/**
 * Servlet implementation class AjoutCandidatureServlet
 */
@WebServlet("/AjoutCandidatureServlet")
public class AjoutCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjoutCandidatureServlet() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");

		IServiceCandidature serviceCandidature = null;
		try {
			serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");

			String adresseEmail = request.getParameter("courriel");
			String adressePostale = request.getParameter("adressePostale");
			String cv = request.getParameter("cv");
			Date dateDepot = new Date();
			Date dateNaissance = Utils.string2Date(request.getParameter("dateNaissance"));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			Integer idNiveauQualification = Integer.parseInt(request.getParameter("niveauQualification"));
			String[] idSecteursActivite = request.getParameterValues("secteursActivite");

			if (id == null)
			{
				serviceCandidature.ajoutCandidature(adresseEmail, adressePostale, cv, dateDepot, dateNaissance, nom, prenom, idNiveauQualification, idSecteursActivite);
			} else {
				serviceCandidature.miseAJourCandidature(id, adresseEmail, adressePostale, cv, dateNaissance, nom, prenom, idNiveauQualification, idSecteursActivite);
			}

		} catch (Exception e) {
			session.setAttribute("errorMessage", e.getLocalizedMessage());
		} finally {
			response.sendRedirect(AssetsLocator.urlForJSP("candidatures/all"));
		}

	}
}
