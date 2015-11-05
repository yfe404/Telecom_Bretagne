package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.telecom_bretagne.cabinet_recrutement.front.utils.AssetsLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.RedirectionHelper;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise;

/**
 * Servlet implementation class AjoutEntrepriseServlet
 */
@WebServlet("/AjoutEntrepriseServlet")
public class AjoutEntrepriseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjoutEntrepriseServlet() { super(); }

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
		Integer userId = (Integer) session.getAttribute("userId");

		if (id != null && userId != null && userId != Integer.parseInt(id)) {
			RedirectionHelper.redirectUnauthorized(session, response);
			return;
		}

		try {
			IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");

			String nom = request.getParameter("nom");
			String descriptif = request.getParameter("descriptif");
			String adresse_postale = request.getParameter("adresse_postale");

			if (id == null)
			{
				serviceEntreprise.ajoutEntreprise(nom, descriptif, adresse_postale);
			}else{
				serviceEntreprise.miseAJourEntreprise(id, nom, descriptif, adresse_postale);
			}

		} catch (Exception e) {
			session.setAttribute("errorMessage", e.getLocalizedMessage());
		} finally {
			if (id == null) {
				response.sendRedirect(AssetsLocator.urlForJSP("enteprises/all"));
			} else {
				response.sendRedirect(AssetsLocator.urlForJSP("my/entreprise/index"));
			}
		}


	}

}
