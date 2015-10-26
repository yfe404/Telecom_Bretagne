package eu.telecom_bretagne.cabinet_recrutement.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocatorException;
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
	public AjoutEntrepriseServlet() {
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
		IServiceEntreprise serviceEntreprise =  null;


		try {
			serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");

			String nom = request.getParameter("nom");
			String descriptif = request.getParameter("descriptif");
			String adresse_postale = request.getParameter("adresse_postale");

			if (id == null)
			{
				serviceEntreprise.ajoutEntreprise(nom, descriptif, adresse_postale);
			}else{
				serviceEntreprise.miseAJourEntreprise(id, nom, descriptif, adresse_postale);
			}

		} catch (ServicesLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			response.sendRedirect("index.jsp");
		}


	}

}
