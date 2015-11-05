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
 * Servlet implementation class SupprimerEntrepriseServlet
 */
@WebServlet("/SupprimerEntrepriseServlet")
public class SupprimerEntrepriseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupprimerEntrepriseServlet() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		Integer userId = (Integer) session.getAttribute("userId");
		
		if(id != null && userId == Integer.parseInt(id) && session.getAttribute("userType").equals("entreprise")){
			try {
				IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");
				serviceEntreprise.supprimerEntreprise(id);
				session.removeAttribute("userType");
				session.removeAttribute("userId");
			} catch(Exception e) {
				session.setAttribute("errorMessage", e.getLocalizedMessage());
			} finally {
				response.sendRedirect(AssetsLocator.urlForJSP("index"));
			}
		} else {
			RedirectionHelper.redirectUnauthorized(session, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
