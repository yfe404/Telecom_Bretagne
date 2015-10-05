package eu.telecom_bretagne.front.gestionServices;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.services.IServicesBeanLocal;

/**
 * Servlet implementation class InsertServiceServlet
 */
@WebServlet("/InsertServiceServlet")
public class InsertServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IServicesBeanLocal sBean;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServiceServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			insertService(request.getParameter("nom"), out);
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
		} finally {
			out.close();
		}
	}

	/**
	 * Méthode pour insérer un nouveau service dans LENTREPRISE
	 */
	protected void insertService(String nom, PrintWriter out)
			throws NamingException {

		afficheInsertService(nom, out);

		// TODO Insère le nouveau service
		// Appel l'EJB correspondant pour le faire

		sBean.insertService(nom);
		
		out.println("</table>");
		out.println("<center><h3> Effectué</h3></center>");
		out.println("<hr>");
		out.println("<a href=\"index.jsp\">Page principale <img src=\"images/return.gif\" alt=\"return\"></a>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void afficheInsertService(String nom, PrintWriter out) {

		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>" + "<h1>Insertion du service :</h1>"
				+ "<table border=\"1\">" + "<tr><b>Nom</b> : " + nom + "<br>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}