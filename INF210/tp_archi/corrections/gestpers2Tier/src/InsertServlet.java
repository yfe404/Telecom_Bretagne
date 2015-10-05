import gestionServices.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
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
			if (request.getParameter("type").equals("personne"))
				afficheEmbauche(out);
			else
				afficheService(out);
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
		} finally {
			out.close();
		}
	}

	/**
	 * Affichage du formulaire d'insertion d'une personne
	 */
	protected void afficheEmbauche(PrintWriter out) throws SQLException {
		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>" + "<h1>Nouvelle embauche<h1>"
				+ "<form action=\"/gestpers2Tier/EmbaucheServlet\">"
				+ "<center><table>" + "<tr><td>Nom</td><td>"
				+ "<input type=\"text\" name=\"nom\">"
				+ "<tr><td>Prénom</td><td>"
				+ "<input type=\"text\" name=\"prenom\">"
				+ "<tr><td>Téléphone</td><td>"
				+ "<input type=\"text\" name=\"telephone\">"
				+ "<tr><td>Fonction</td><td>"
				+ "<input type=\"text\" name=\"fonction\">"
				+ "<tr><td>Service</td><td>" + "<select name=\"service\">");

		// Partie dynamique de la page
		for (Service s : services("id", "asc"))
			// Infos sur les services
			out.println("<option value=\"" + s.getNum() + "\">" + s.getNom()
					+ "</option>");

		out.println("</select></table><br>"
				+ "<input type=\"submit\" value=\"Insérer\">"
				+ "</form></center>" + "</body></html>");
	}

	/**
	 * Affichage du formulaire d'insertion d'un servcie
	 */
	protected void afficheService(PrintWriter out) throws SQLException {
		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>" + "<h1>Nouveau service<h1>"
				+ "TODO : formulaire d'ajout d'un service <hr>"
				+ "<a href=\"index.jsp\">Page principale <img src=\"images/return.gif\" alt=\"return\"></a>");
		out.println("</body></html>");
	}

	/*****************************************************
	 * Cette méthode rend la liste des services de LENTREPRISE
	 * 
	 * @param tri
	 * @param ordre
	 * @throws SQLException
	 ******************************************************/
	protected List<Service> services(String tri, String ordre)
			throws SQLException {
		ResultSet rset;

		// Connexion à la base de données
		Connection connexion = createConnexion();
		List<Service> result = new ArrayList<Service>();

		// Récupération de la liste des services
		try {
			Statement stmt = connexion.createStatement();
			System.out.println("select * from services order by " + tri + " "
					+ ordre);
			rset = stmt.executeQuery("select * from services order by " + tri
					+ " " + ordre);
			while (rset.next())
				result.add(new Service(rset.getString(1), rset.getString(2)));
			stmt.close();
		} catch (SQLException sqle) {
			throw new SQLException("Problème à la lecture dans la base : "
					+ sqle);
		}

		try {
			connexion.close();
		} catch (SQLException sqle) {
			throw new SQLException("Problème à la déconnexion : " + sqle);
		}
		return result;
	}

	private Connection createConnexion() throws SQLException {
		try {
			Context it = new InitialContext();
			DataSource ds = (DataSource) it
					.lookup("java:comp/env/jdbc/gestPers2Tier");
			Connection connexion = ds.getConnection();
			return connexion;
		} catch (SQLException sqle) {
			throw new SQLException("Impossible d'accéder à la base : " + sqle);
		} catch (NamingException e) {
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
