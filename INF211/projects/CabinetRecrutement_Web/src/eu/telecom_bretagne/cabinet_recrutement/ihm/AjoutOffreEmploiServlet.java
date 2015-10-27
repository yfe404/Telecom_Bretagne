package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;
import java.util.Date;
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

		String id = request.getParameter("id");
		IServiceOffreEmploi serviceOffreEmploi =  null;


		try {
			serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");

			String titre = request.getParameter("titre");
			String descriptionMission = request.getParameter("descriptifMission");
			String profilRecherche = request.getParameter("profilRecherche");
			NiveauQualification niveauQualificationBean = null;    /** @todo : get parameter from jsp **/
			Set<SecteurActivite> secteurActivites = null;    /** @todo : get parameter from jsp **/
			
			if (id == null)
			{
				serviceOffreEmploi.ajoutOffreEmploi(titre, descriptionMission, profilRecherche, niveauQualificationBean, secteurActivites);
			}else{
				serviceOffreEmploi.miseAJourOffreEmploi(id, titre, descriptionMission, profilRecherche, niveauQualificationBean, secteurActivites);
			} 

		} catch (ServicesLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			response.sendRedirect("liste_offre_emploi.jsp");
		}

	}
}
