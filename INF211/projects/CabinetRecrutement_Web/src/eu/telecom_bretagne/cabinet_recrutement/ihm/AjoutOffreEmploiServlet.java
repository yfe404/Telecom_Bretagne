package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;
import java.util.Date;
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
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocatorException;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature;
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
	public AjoutOffreEmploiServlet() {
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
		HttpSession session = request.getSession();
		String id = request.getParameter("id");

		IServiceIndexation serviceIndexation = null;
		try {
			serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance().getRemoteInterface("ServiceIndexation");
		} catch (ServicesLocatorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response.sendRedirect("liste_offre_emploi.jsp");
		}

		IServiceEntreprise serviceEntreprise = null;
		try {
			serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");
		} catch (ServicesLocatorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response.sendRedirect("liste_offre_emploi.jsp");
		}

		IServiceOffreEmploi serviceOffreEmploi = null;
		try {
			serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");

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

		} catch (ServicesLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			response.sendRedirect("liste_offre_emploi.jsp");
		}

	}
}
