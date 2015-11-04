package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteurActiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Session Bean implementation class ServiceCandidature
 * 
 * @author Luke Skywalker
 */
@Stateless
@LocalBean
public class ServiceCandidature implements IServiceCandidature
{
	//-----------------------------------------------------------------------------
	@EJB
	private CandidatureDAO candidatureDAO;
	
	@EJB
	private SecteurActiviteDAO secteurActiviteDAO;

	public ServiceCandidature() {
		// Default constructor
	}
	
	
	@Override
	public Candidature getCandidature(int id) {
		// TODO Auto-generated method stub		
		return candidatureDAO.findById(id);
	}

	@Override
	public List<Candidature> listeDesCandidatures() {
		return candidatureDAO.findAll();
	}

	@Override
	public void ajoutCandidature(String adresseEmail, String adressePostale,
			String cv, Date dateDepot, Date dateNaissance, String nom,
			String prenom, NiveauQualification niveauQualificationBean,
			String[] secteurActivites) {
		
		Candidature candidature = new Candidature();
		candidature.setAdresseEmail(adresseEmail);
		candidature.setAdressePostale(adressePostale);
		candidature.setCv(cv);
		candidature.setDateDepot(dateDepot);
		candidature.setDateNaissance(dateNaissance);
		candidature.setNom(nom);
		candidature.setPrenom(prenom);
		candidature.setNiveauQualificationBean(niveauQualificationBean);
		
		candidature.setSecteurActivites(new HashSet<SecteurActivite>());
		
		for (String secteurActiviteId : secteurActivites) {
			SecteurActivite secteurActivite = secteurActiviteDAO.findById(new Integer(secteurActiviteId));
			candidature.getSecteurActivites().add(secteurActivite);
			secteurActivite.getCandidatures().add(candidature);
			secteurActiviteDAO.update(secteurActivite);
		}
		
		candidatureDAO.persist(candidature);
	}

	@Override
	public void miseAJourCandidature(String id, String adresseEmail, String adressePostale,
			String cv, Date dateNaissance, String nom,
			String prenom, NiveauQualification niveauQualificationBean,
			String[] secteurActivites) {
		
		
		Candidature candidature = candidatureDAO.findById(Integer.parseInt(id));
		if (candidature != null){
			for (SecteurActivite secteurActivite : candidature.getSecteurActivites()) {
				secteurActivite.getCandidatures().remove(candidature);
				secteurActiviteDAO.update(secteurActivite);
			}
			
			candidature.setAdresseEmail(adresseEmail);
			candidature.setAdressePostale(adressePostale);
			candidature.setCv(cv);
			candidature.setDateNaissance(dateNaissance);
			candidature.setNom(nom);
			candidature.setPrenom(prenom);
			candidature.setNiveauQualificationBean(niveauQualificationBean);
			
			for (String secteurActiviteId : secteurActivites) {
				SecteurActivite secteurActivite = secteurActiviteDAO.findById(new Integer(secteurActiviteId));
				candidature.getSecteurActivites().add(secteurActivite);
				secteurActivite.getCandidatures().add(candidature);
				secteurActiviteDAO.update(secteurActivite);
			}
			
			candidatureDAO.update(candidature);
		}
	}

	@Override
	public void supprimerCandidature(String id) {
		Candidature cand = candidatureDAO.findById(Integer.parseInt(id));
		if (cand != null){
			candidatureDAO.remove(cand);
		}	
	}
}





