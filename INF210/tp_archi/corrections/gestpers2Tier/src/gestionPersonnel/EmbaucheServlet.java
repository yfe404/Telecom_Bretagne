package gestionPersonnel;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.*;

/**
 * Servlet implementation class EmbaucheServlet
 */
@WebServlet("/EmbaucheServlet")
public class EmbaucheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmbaucheServlet() {
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
			embauche(request.getParameter("nom"),
					request.getParameter("prenom"),
					request.getParameter("telephone"),
					request.getParameter("fonction"),
					request.getParameter("service"), out);
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
		} finally {
			out.close();
		}
	}

	/**
	 * Méthode pour embauche une nouvelle personne dans LENTREPRISE
	 */
	protected void embauche(String nom, String prenom, String telephone,
			String fonction, String service, PrintWriter out)
			throws SQLException {

		afficheEmbauche(nom, prenom, telephone, fonction, service, out);

		// Connexion à la base de données
		Connection connexion = createConnexion();
		// Embauche
		try {
			Statement stmt = connexion.createStatement();
			// Ajout dans la BD
			System.out.println("insert into personnes values(" + nom + ","
					+ prenom + "," + telephone + "," + fonction + "," + service
					+ ")");

			stmt.executeUpdate("insert into personnes(nom,prenom,telephone,fonction,app_serv) "
					+ "values('"
					+ nom
					+ "','"
					+ prenom
					+ "','"
					+ telephone
					+ "','"
					+ fonction
					+ "','"
					+ Integer.parseInt(service)
					+ "')");

		} catch (SQLException sqle) {
			// Annuler la transaction si problème
			connexion.rollback();
			throw new SQLException(
					"Problème à la lecture/écriture dans la base : " + sqle);
		}

		try {
			connexion.close();
		} catch (SQLException sqle) {
			// Annuler la transaction si problème
			connexion.rollback();
			throw new SQLException("Problème à la déconnexion : " + sqle);
		}

		out.println("</table>");
		out.println("<center><h3> Effectué</h3></center>");
		out.println("<hr>");
		out.println("<a href=\"index.jsp\">Page principale <img src=\"images/return.gif\" alt=\"return\"></a>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void afficheEmbauche(String nom, String prenom, String telephone,
			String fonction, String service, PrintWriter out) {

		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>" + "<h1>Embauche de :</h1>" + "<table border=\"1\">"
				+ "<tr><b>Nom</b> : " + nom + "<br>" + "<tr><b>Prénom</b> : "
				+ prenom + "<br>" + "<tr><b>Téléphone</b> : " + telephone
				+ "<br>" + "<tr><b>Fonction</b> : " + fonction + "<br>"
				+ "<tr><b>Service</b> : " + service + "<br>");
	}
    /**
     * Méthode de création d'une connexion à la BD
     * @return la connexion à utiliser
     * @throws SQLException
     */
    private Connection createConnexion() 
       throws SQLException {
       try{
           Context it = new InitialContext();
           DataSource ds = (DataSource)it.lookup("java:comp/env/jdbc/gestPers2Tier");
           Connection connexion = ds.getConnection();
           return connexion;
       } catch(SQLException sqle){
           throw new SQLException("Impossible d'accéder à la base : " + sqle);
       } catch(NamingException e) {
           throw new SQLException("Impossible de trouver la base : " + e);
       }
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
