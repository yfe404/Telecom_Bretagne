package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

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


	@Override
	public void supprimerOffreEmploi(String id) {
		OffreEmploi offre = offreEmploiDAO.findById(Integer.parseInt(id));
		if (offre != null){
			offreEmploiDAO.remove(offre);
		}	
	}


	@Override
	public void ajoutOffreEmploi(String titre, String descriptionMission,
			String profilRecherche, Entreprise entreprise,
			NiveauQualification niveauQualificationBean,
			Set<SecteurActivite> secteurActivites) {

		OffreEmploi offreEmploi = new OffreEmploi();

		offreEmploi.setTitre(titre);
		offreEmploi.setDescriptionMission(descriptionMission);
		offreEmploi.setProfilRecherche(profilRecherche);
		offreEmploi.setEntrepriseBean(entreprise);
		offreEmploi.setNiveauQualificationBean(niveauQualificationBean);
		offreEmploi.setSecteurActivites(secteurActivites);
		offreEmploi.setDateDepot(new Date());


		offreEmploiDAO.persist(offreEmploi);		

	}


	@Override
	public void miseAJourOffreEmploi(String id, String titre,
			String descriptionMission, String profilRecherche, Entreprise entreprise,
			NiveauQualification niveauQualificationBean,
			Set<SecteurActivite> secteurActivites) {


		OffreEmploi offreEmploi = offreEmploiDAO.findById(Integer.parseInt(id));
		if (offreEmploi != null){

			offreEmploi.setTitre(titre);
			offreEmploi.setDescriptionMission(descriptionMission);
			offreEmploi.setProfilRecherche(profilRecherche);
			offreEmploi.setEntrepriseBean(entreprise);
			offreEmploi.setNiveauQualificationBean(niveauQualificationBean);
			offreEmploi.setSecteurActivites(secteurActivites);
			offreEmploi.setDateDepot(new Date());

			offreEmploiDAO.update(offreEmploi);
		}

	}

}





