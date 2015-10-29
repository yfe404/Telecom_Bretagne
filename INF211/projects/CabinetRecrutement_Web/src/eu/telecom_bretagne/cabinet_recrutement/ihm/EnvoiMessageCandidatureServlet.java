package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageCandidature;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi;

/**
 * Servlet implementation class EnvoiMessageCandidatureServlet
 */
@WebServlet("/EnvoiMessageCandidatureServlet")
public class EnvoiMessageCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnvoiMessageCandidatureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
			OffreEmploi offreEmploi = serviceOffreEmploi.getOffreEmploi(Integer.parseInt(request.getParameter("idOffreEmploi")));
			
			IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
			Candidature candidature = serviceCandidature.getCandidature(Integer.parseInt(request.getParameter("idCandidature")));
			
			IServiceMessageCandidature serviceMessageCandidature = (IServiceMessageCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceMessageCandidature");
			serviceMessageCandidature.ajoutMessageCandidature(offreEmploi, candidature, request.getParameter("message"), new Date());
		} catch (Exception e) {
			session.setAttribute("errorMessage", e.getLocalizedMessage());
		}
		
		response.sendRedirect("message_candidature.jsp?idMessage=TODO");
	}

}
