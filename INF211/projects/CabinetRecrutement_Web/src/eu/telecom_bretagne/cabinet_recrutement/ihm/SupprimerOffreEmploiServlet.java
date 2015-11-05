package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.AssetsLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.RedirectionHelper;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi;

/**
 * Servlet implementation class SupprimerOffreEmploiServlet
 */
@WebServlet("/SupprimerOffreEmploiServlet")
public class SupprimerOffreEmploiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupprimerOffreEmploiServlet() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		Integer userId = (Integer) session.getAttribute("userId");

		if(id != null){
			try {
				IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
				OffreEmploi offreEmploi = serviceOffreEmploi.getOffreEmploi(Integer.parseInt(id));

				Integer i = offreEmploi.getEntrepriseBean().getId();

				if (session.getAttribute("userType").equals("entreprise") && offreEmploi.getEntrepriseBean().getId().equals(userId)) {
					serviceOffreEmploi.supprimerOffreEmploi(id);
					response.sendRedirect(AssetsLocator.urlForJSP("my/entreprise/offres"));
				} else {
					RedirectionHelper.redirectUnauthorized(session, response);
				}
			} catch(Exception e) {
				session.setAttribute("errorMessage", e.getLocalizedMessage());
				response.sendRedirect(AssetsLocator.urlForJSP("my/entreprise/offres"));
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}


}
