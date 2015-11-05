package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		HttpSession session = request.getSession();
		
		session.removeAttribute("userType");
		session.removeAttribute("userId");
		
		// (a) Déconnexion
		if (request.getParameter("logout") != null) {
			response.sendRedirect("index.jsp");
			return;
		}

		// (b) Connexion
		// - Identifiant null ou vide
		if (loginId == null || loginId.trim().isEmpty()) {
			session.setAttribute("errorMessage", "Veuillez entrer votre identifiant.");
			response.sendRedirect("index.jsp");
			return;
		}

		// - Chargement des services
		IServiceCandidature serviceCandidature = null;
		IServiceEntreprise serviceEntreprise = null;

		try {
			serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
			serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");
		} catch (Exception e) {
			session.setAttribute("errorMessage", e.getLocalizedMessage());
			response.sendRedirect("index.jsp");
			return;
		}

		// - Récupération de l'id
		loginId = loginId.trim();
		Integer userId = null;

		try {
			userId = Integer.parseInt(loginId.split("_")[1]);
		} catch (Exception e) {
			session.setAttribute("errorMessage", "Identifiant inconnu.");
			response.sendRedirect("index.jsp");
			return;
		}
		
		// - Détection du type d'utilisateur
		// - Vérification de l'existence de l'utilisateur
		if (loginId.startsWith("CAND_") && serviceCandidature.getCandidature(userId) != null) {
			session.setAttribute("userType", "candidat");
			session.setAttribute("userId", userId);
		}

		if (loginId.startsWith("ENT_") && serviceEntreprise.getEntreprise(userId) != null) {
			session.setAttribute("userType", "entreprise");
			session.setAttribute("userId", userId);
		}
		
		if (session.getAttribute("userId") == null) {
			session.setAttribute("errorMessage", "Identifiant inconnu.");
		}

		response.sendRedirect("index.jsp");
	}

}
