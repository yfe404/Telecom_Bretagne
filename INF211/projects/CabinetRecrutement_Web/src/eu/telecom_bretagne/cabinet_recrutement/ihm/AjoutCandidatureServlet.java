package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocatorException;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation;

/**
 * Servlet implementation class AjoutCandidatureServlet
 */
@WebServlet("/AjoutCandidatureServlet")
public class AjoutCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjoutCandidatureServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		IServiceCandidature serviceCandidature = null;
		try {
			serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");

			String adresseEmail = request.getParameter("courriel");
			String adressePostale = request.getParameter("adressePostale");
			String cv = request.getParameter("cv");
			Date dateDepot = new Date();
			Date dateNaissance = new Date(request.getParameter("dateNaissance"));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			Integer idNiveauQualification = Integer.parseInt(request.getParameter("niveauQualification"));
			String[] idSecteursActivite = request.getParameterValues("secteursActivite");

			if (id == null)
			{
				serviceCandidature.ajoutCandidature(adresseEmail, adressePostale, cv, dateDepot, dateNaissance, nom, prenom, idNiveauQualification, idSecteursActivite);
			}else{
				serviceCandidature.miseAJourCandidature(id, adresseEmail, adressePostale, cv, dateNaissance, nom, prenom, idNiveauQualification, idSecteursActivite);
			}

		} catch (ServicesLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			response.sendRedirect("liste_candidatures.jsp");
		}

	}
}
