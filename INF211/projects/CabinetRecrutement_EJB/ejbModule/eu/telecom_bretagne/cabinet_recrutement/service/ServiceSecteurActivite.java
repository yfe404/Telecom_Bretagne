package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteurActiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Session Bean implementation class ServiceSecteurActivite
 * 
 * @author Luke Skywalker
 */
@Stateless
@LocalBean
public class ServiceSecteurActivite implements IServiceSecteurActivite
{
	//-----------------------------------------------------------------------------
	@EJB
	private SecteurActiviteDAO secteurActiviteDAO;

	public ServiceSecteurActivite() {
		// Default constructor
	}
	
	@Override
	public SecteurActivite getSecteurActivite(int id) {
		return secteurActiviteDAO.findById(id);
	}
	
	@Override
	public List<SecteurActivite> listeDesSecteursActivite() {
		return secteurActiviteDAO.findAll();
	}
	
//	public Candidature getCandidature(int id) {
//		// TODO Auto-generated method stub		
//		return candidatureDAO.findById(id);
//	}
//
//	@Override
//	public List<Candidature> listeDesCandidatures() {
//		return candidatureDAO.findAll();
//	}
//
//	@Override
//	public void ajoutCandidature(String adresseEmail, String adressePostale,
//			String cv, Date dateDepot, Date dateNaissance, String nom,
//			String prenom, NiveauQualification niveauQualificationBean,
//			Set<SecteurActivite> secteurActivites) {
//		
//	}
//
//	@Override
//	public void miseAJourCandidature(String id, String nom, String descriptif,
//			String adresse_postale) {
//	}
//
//	@Override
//	public void supprimerCandidature(String id) {
//		// TODO Auto-generated method stub
//		
//	}
}





