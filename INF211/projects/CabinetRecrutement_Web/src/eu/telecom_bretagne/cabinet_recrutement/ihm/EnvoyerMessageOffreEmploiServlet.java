package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.AssetsLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocatorException;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageOffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi;

/**
 * Servlet implementation class EnvoyerMessageCandidatServlet
 * Envoyer un message à partir d'une offre d'emploi (accessible uniquement à une entreprise loggée)
 */
@WebServlet("/EnvoyerMessageOffreEmploiServlet")
public class EnvoyerMessageOffreEmploiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnvoyerMessageOffreEmploiServlet() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		IServiceMessageOffreEmploi serviceMessageOffreEmploi =  null;
		IServiceCandidature serviceCandidature = null;
		IServiceOffreEmploi serviceOffreEmploi = null;


		try {
			serviceMessageOffreEmploi = (IServiceMessageOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceMessageOffreEmploi");
			serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
			serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");

			Candidature candidature = serviceCandidature.getCandidature(Integer.parseInt(request.getParameter("idCandidature")));
			OffreEmploi offreEmploi = serviceOffreEmploi.getOffreEmploi(Integer.parseInt(request.getParameter("idOffreEmploi")));

			String corpsMessage = request.getParameter("message");
			Date dateEnvoi = new Date();

			serviceMessageOffreEmploi.ajoutMessageOffreEmploi(offreEmploi, candidature, corpsMessage, dateEnvoi);

		} catch (ServicesLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			response.sendRedirect(AssetsLocator.urlForJSP("my/candidature/inbox")); /** @todo remplir avec la bonne redirection **/
		}

	}
}
