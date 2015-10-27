package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

/**
 * Session Bean implementation class ServiceOffreEmploi
 * 
 * @author Luke Skywalker
 */
@Stateless
@LocalBean
public class ServiceOffreEmploi implements IServiceOffreEmploi
{
	//-----------------------------------------------------------------------------
	@EJB
	private OffreEmploiDAO offreEmploiDAO;

	public ServiceOffreEmploi() {
		// Default constructor
	}
	
	
	@Override
	public OffreEmploi getOffreEmploi(int id) {
		return offreEmploiDAO.findById(id);
	}
	
	@Override
	public List<OffreEmploi> listeDesOffresEmploi() {
		return offreEmploiDAO.findAll();
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





