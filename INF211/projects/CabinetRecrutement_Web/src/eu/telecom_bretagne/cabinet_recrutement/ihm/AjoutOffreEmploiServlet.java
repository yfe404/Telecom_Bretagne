package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.AssetsLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi;

/**
 * Servlet implementation class AjoutOffreEmploiServlet
 */
@WebServlet("/AjoutOffreEmploiServlet")
public class AjoutOffreEmploiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjoutOffreEmploiServlet() { super(); }

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

		IServiceIndexation serviceIndexation = null;
		IServiceEntreprise serviceEntreprise = null;
		IServiceOffreEmploi serviceOffreEmploi = null;

		try {
			serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance().getRemoteInterface("ServiceIndexation");
			serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");
			serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
		} catch (Exception e) {
			session.setAttribute("errorMessage", e.getLocalizedMessage());
			response.sendRedirect(AssetsLocator.urlForJSP("my/entreprise/offres"));
		}

		try {
			String titre = request.getParameter("titre");
			String descriptionMission = request.getParameter("descriptionMission");
			String profilRecherche = request.getParameter("profilRecherche");

			Entreprise entreprise = serviceEntreprise.getEntreprise((Integer) session.getAttribute("userId"));

			NiveauQualification niveauQualificationBean = serviceIndexation.getNiveauQualification(Integer.parseInt(request.getParameter("niveauQualification")));

			Set<SecteurActivite> secteursActivite = new HashSet<>();
			for (String secteurActiviteId : request.getParameterValues("secteursActivite")) {
				secteursActivite.add(serviceIndexation.getSecteurActiviteById(Integer.parseInt(secteurActiviteId)));
			}

			if (id == null)
			{
				serviceOffreEmploi.ajoutOffreEmploi(titre, descriptionMission, profilRecherche, entreprise, niveauQualificationBean, secteursActivite);
			}else{
				serviceOffreEmploi.miseAJourOffreEmploi(id, titre, descriptionMission, profilRecherche, entreprise, niveauQualificationBean, secteursActivite);
			} 

		} catch (Exception e) {
			session.setAttribute("errorMessage", e.getLocalizedMessage());
		} finally {
			response.sendRedirect(AssetsLocator.urlForJSP("my/entreprise/offres"));
		}

	}
}
