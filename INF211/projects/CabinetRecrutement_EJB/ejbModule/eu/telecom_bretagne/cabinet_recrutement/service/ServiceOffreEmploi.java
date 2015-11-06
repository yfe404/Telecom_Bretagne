package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualificationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteurActiviteDAO;
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

	@EJB
	private NiveauQualificationDAO niveauQualificationDAO;

	@EJB
	private SecteurActiviteDAO secteurActiviteDAO;

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
	public List<OffreEmploi> listeDesOffresEmploi(int idEntreprise) {
		return offreEmploiDAO.findByEntreprise(idEntreprise);
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
			Integer idNiveauQualification,
			String[] idSecteurActivites) {

		OffreEmploi offreEmploi = new OffreEmploi();

		offreEmploi.setTitre(titre);
		offreEmploi.setDescriptionMission(descriptionMission);
		offreEmploi.setProfilRecherche(profilRecherche);
		offreEmploi.setEntrepriseBean(entreprise);
		offreEmploi.setDateDepot(new Date());
		
		NiveauQualification niveauQualification = niveauQualificationDAO.findById(idNiveauQualification);
		offreEmploi.setNiveauQualificationBean(niveauQualification);

		offreEmploi.setSecteurActivites(new HashSet<SecteurActivite>());
		for (String idSecteurActivite : idSecteurActivites) {
			SecteurActivite secteurActivite = secteurActiviteDAO.findById(Integer.parseInt(idSecteurActivite));
			offreEmploi.getSecteurActivites().add(secteurActivite);
		}

		offreEmploiDAO.persist(offreEmploi);
		
		for (String idSecteurActivite : idSecteurActivites) {
			SecteurActivite secteurActivite = secteurActiviteDAO.findById(Integer.parseInt(idSecteurActivite));
			secteurActivite.getOffreEmplois().add(offreEmploi);
			secteurActiviteDAO.update(secteurActivite);
		}
	}


	@Override
	public void miseAJourOffreEmploi(String id, String titre,
			String descriptionMission, String profilRecherche, Entreprise entreprise,
			Integer idNiveauQualification,
			String[] idSecteurActivites) {


		OffreEmploi offreEmploi = offreEmploiDAO.findById(Integer.parseInt(id));
		if (offreEmploi != null){
			for (SecteurActivite secteurActivite : offreEmploi.getSecteurActivites()) {
				secteurActivite.getOffreEmplois().remove(offreEmploi);
				secteurActiviteDAO.update(secteurActivite);
			}
			
			offreEmploi.setTitre(titre);
			offreEmploi.setDescriptionMission(descriptionMission);
			offreEmploi.setProfilRecherche(profilRecherche);
			offreEmploi.setEntrepriseBean(entreprise);
			offreEmploi.setDateDepot(new Date());

			NiveauQualification niveauQualification = niveauQualificationDAO.findById(idNiveauQualification);
			offreEmploi.setNiveauQualificationBean(niveauQualification);
			
			offreEmploi.setSecteurActivites(new HashSet<SecteurActivite>());
			for (String secteurActiviteId : idSecteurActivites) {
				SecteurActivite secteurActivite = secteurActiviteDAO.findById(new Integer(secteurActiviteId));
				offreEmploi.getSecteurActivites().add(secteurActivite);
				secteurActivite.getOffreEmplois().add(offreEmploi);
				secteurActiviteDAO.update(secteurActivite);
			}

			offreEmploiDAO.update(offreEmploi);
		}

	}

}





