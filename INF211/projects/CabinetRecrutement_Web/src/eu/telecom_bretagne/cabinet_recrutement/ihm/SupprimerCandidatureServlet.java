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
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature;

/**
 * Servlet implementation class SupprimerCandidatureServlet
 */
@WebServlet("/SupprimerCandidatureServlet")
public class SupprimerCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerCandidatureServlet() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		
    	String id = request.getParameter("id");
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (id != null && userId != null && session.getAttribute("userType").equals("candidat") && userId == Integer.parseInt(id)) {
			try {
				IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
				serviceCandidature.supprimerCandidature(id);
				session.removeAttribute("userType");
				session.removeAttribute("userId");
			} catch (Exception e){
				session.setAttribute("errorMessage", "Identifiant inconnu.");
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
