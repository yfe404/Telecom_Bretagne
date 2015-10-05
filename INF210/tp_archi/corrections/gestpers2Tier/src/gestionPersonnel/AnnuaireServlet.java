package gestionPersonnel;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.*;

/**
 * Servlet implementation class AnnuaireServlet
 */
@WebServlet("/AnnuaireServlet")
public class AnnuaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnnuaireServlet() {
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
			annuaire(request.getParameter("tri"),
					request.getParameter("ordre"), out);
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
		} finally {
			out.close();
		}

	}
	
	  /** 
     * Liste des personnes de LENTREPRISE
     * @param tri définit le critère de tri de la liste
     * @param ordre définit l'ordre (ascendant,descendent)
     * @param out
     */
    protected void annuaire(String tri, String ordre, PrintWriter out)
            throws SQLException, IOException {
        // Par défaut la servlet rend une liste ordonnée (ascendant) par le numéro de personne
        if (tri == null) {
            tri = "id";
            ordre = "asc";
        }

        // Page HTML affichant la liste des personnes
        affichePersonnes(out);

        // Obtention des services de la base de données
        for (Personne p : personnes(tri, ordre)) {
            out.println(p.toHtml());
        }

        out.println("    </table>");
        out.println("  </center><hr>");
        out.println("<a href=\"index.jsp\">Page principale <img src=\"images/return.gif\" alt=\"return\"></a>");
        out.println("</body>");
        out.println("</html>");
    }
    
    /*****************************************************
     * Cette méthode s'occupe de l'affichage de la page HTML
     * donnant la liste de toutes les personnes
     ******************************************************/
    protected void affichePersonnes(PrintWriter out)
            throws IOException {
        out.println("<html>"
                + "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
                + "<body>"
                + "<h1>Annuaire de LENTREPRISE</h1>"
                + "<center>"
                + "<table border=\"1\">"
                + "<th>Num_pers<br>"
                + "<a href=\"/gestpers2Tier/AnnuaireServlet?tri=id&ordre=asc\">A</a> "
                + "<a href=\"/gestpers2Tier/AnnuaireServlet?tri=id&ordre=desc\">D</a></th>"
                + "<th>Nom<br>"
                + "<a href=\"/gestpers2Tier/AnnuaireServlet?tri=nom&ordre=asc\">A</a> "
                + "<a href=\"/gestpers2Tier/AnnuaireServlet?tri=nom&ordre=desc\">D</a></th>"
                + "<th>Prénom<br>"
                + "<a href=\"/gestpers2Tier/AnnuaireServlet?tri=prenom&ordre=asc\">A</a> "
                + "<a href=\"/gestpers2Tier/AnnuaireServlet?tri=prenom&ordre=desc\">D</a></th>"
                + "<th>Téléphone</th>"
                + "<th>Fonction</th>"
                + "<th>Service<br>"
                + "<a href=\"/gestpers2Tier/AnnuaireServlet?tri=app_serv&ordre=asc\">A</a> "
                + "<a href=\"/gestpers2Tier/AnnuaireServlet?tri=app_serv&ordre=desc\">D</a></th>");
    }
    
    protected List<Personne> personnes(String tri, String ordre)
            throws SQLException {
        ResultSet rset;

        // Connexion à la base de données
        Connection connexion = createConnexion();
        List<Personne> result = new ArrayList<Personne>();

        // Récupération de la liste des personne
        try {
            Statement stmt = connexion.createStatement();
            System.out.println("select p.id, p.nom, prenom, telephone, fonction, s.nom from personnes p, services s where "
                    + "p.app_serv=s.id order by p." + tri + " " + ordre);
            rset = stmt.executeQuery("select  p.id, p.nom, prenom, telephone, fonction, s.nom from personnes p, services s where "
                    + "p.app_serv=s.id order by p." + tri + " " + ordre);
            while (rset.next()) {
                result.add(new Personne(rset.getString(1), rset.getString(2),
                        rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6)));
            }
            stmt.close();
        } catch (SQLException sqle) {
            throw new SQLException("Problème à la lecture dans la base : " + sqle);
        }

        try {
            connexion.close();
        } catch (SQLException sqle) {
            // Annuler la transaction car problème
            throw new SQLException("Problème à la déconnexion : " + sqle);
        }
        return result;
    }
    
    /**
     * Méthode de création d'une connexion à la BD
     * @return la connexion à utiliser
     * @throws SQLException
     */
    private Connection createConnexion()
            throws SQLException {
        try {
            Context it = new InitialContext();
            DataSource ds = (DataSource) it.lookup("java:comp/env/jdbc/gestPers2Tier");
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
