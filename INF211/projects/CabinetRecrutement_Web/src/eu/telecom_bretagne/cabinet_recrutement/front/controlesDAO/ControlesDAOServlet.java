package eu.telecom_bretagne.cabinet_recrutement.front.controlesDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.EntrepriseDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualificationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteurActiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocatorException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/ControlesDAO")
public class ControlesDAOServlet extends HttpServlet
{
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlesDAOServlet()
	{
		super();
	}
	//-----------------------------------------------------------------------------
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Flot de sortie pour écriture des résultats.
    PrintWriter out = response.getWriter();
    
    // Récupération de la réféence vers le(s) DAO(s)
		EntrepriseDAO entrepriseDAO = null;
		CandidatureDAO candidatureDAO = null;
		NiveauQualificationDAO niveauQualificationDAO = null;
		OffreEmploiDAO offreEmploiDAO = null;
		SecteurActiviteDAO secteurActiviteDAO = null;
    try
    {
	    entrepriseDAO = (EntrepriseDAO) ServicesLocator.getInstance().getRemoteInterface("EntrepriseDAO");
	    candidatureDAO = (CandidatureDAO) ServicesLocator.getInstance().getRemoteInterface("CandidatureDAO");
	    niveauQualificationDAO = (NiveauQualificationDAO) ServicesLocator.getInstance().getRemoteInterface("NiveauQualificationDAO");
	    offreEmploiDAO = (OffreEmploiDAO) ServicesLocator.getInstance().getRemoteInterface("OffreEmploiDAO");
	    secteurActiviteDAO = (SecteurActiviteDAO) ServicesLocator.getInstance().getRemoteInterface("SecteurActiviteDAO");
    }
    catch (ServicesLocatorException e)
    {
    	e.printStackTrace();
    }
		out.println("Contrôles de fonctionnement du DAO EntrepriseDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		out.println("Liste des entreprises :");
		List<Entreprise> entreprises = entrepriseDAO.findAll();
		
		for(Entreprise entreprise : entreprises)
		{
			out.print(entreprise.getId() + " - "); out.println(entreprise.getNom());
		}
		out.println();
		
		out.println("Contrôles de fonctionnement du DAO CandidatureDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		out.println("Ajout candidature");
		Candidature c1 = new Candidature();
		c1.setAdresseEmail("j@j.org");
		c1.setAdressePostale("rue de la poste");
		c1.setCv("pas de boulot");
		c1.setDateDepot(new Date());
		c1.setDateNaissance(new Date());
		c1.setNom("Jean");
		c1.setPrenom("Bon");
		c1.setNiveauQualificationBean(niveauQualificationDAO.findById(1));
		
		
		SecteurActivite s = secteurActiviteDAO.findById(1);
		c1.setSecteurActivites(new HashSet<SecteurActivite>());
		c1.getSecteurActivites().add(s);
		s.getCandidatures().add(c1);
		
		secteurActiviteDAO.update(s);
		candidatureDAO.persist(c1);
		
		
		
		out.println("Liste des candidatures :");
		List<Candidature> candidatures = candidatureDAO.findBySecteurActiviteAndNiveauQualificaion(2, 1);
		
		for(Candidature candidature : candidatures)
		{
			out.println(candidature.getNom());
		}
		out.println();
		
		
		
		out.println("Contrôles de fonctionnement du DAO NiveauQualificationDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		out.println("Liste des niveau de qualification :");
		List<NiveauQualification> niveauxQualification = niveauQualificationDAO.findAll();
		
		for(NiveauQualification niveauQualification : niveauxQualification)
		{
			out.println(niveauQualification.getIntitule());
		}
		out.println();
		
		
		out.println("Contrôles de fonctionnement du DAO OffreEmploiDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		out.println("Liste des offres d'emploi :");
		List<OffreEmploi> offresEmploi = offreEmploiDAO.findBySecteurActiviteAndNiveauQualification(1, 2);
		
		for(OffreEmploi offreEmploi : offresEmploi)
		{
			out.println(offreEmploi.getTitre());
		}
		out.println();
		

		
		
		out.println("Contrôles de fonctionnement du DAO SecteurActiviteDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		out.println("Liste des secteurs d'activite :");
		List<SecteurActivite> secteurs = secteurActiviteDAO.findAll();
		
		for(SecteurActivite secteur : secteurs)
		{
			out.println(secteur.getIntitule());
		}
		out.println();
		

		

		
	}
	//-----------------------------------------------------------------------------
}
