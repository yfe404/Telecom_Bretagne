package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualificationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteurActiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;



/**
 * Session Bean implementation class NiveauQualification and SecteurActivite
 * 
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class ServiceIndexation implements IServiceIndexation{

	@EJB
	private NiveauQualificationDAO niveauQualificationDAO;

	@EJB
	private SecteurActiviteDAO secteurActiviteDAO;

	@EJB
	private OffreEmploiDAO offreEmploiDAO;

	@EJB
	private CandidatureDAO candidatureDAO;

	@Override
	public List<SecteurActivite> listeDesSecteursActivite() {
		return secteurActiviteDAO.findAll();
	}

	@Override
	public List<NiveauQualification> listeDesNiveauxQualification() {
		return niveauQualificationDAO.findAll();
	}

	@Override
	public SecteurActivite getSecteurActiviteById(int id) {
		return secteurActiviteDAO.findById(id);
	}

	@Override
	public NiveauQualification getNiveauQualification(int id) {
		return niveauQualificationDAO.findById(id);
	}

	@Override
	public List<Candidature> getMatchingCandidaturesForOffreEmploi(int idOffreEmploi) {
		List<Candidature> matchingCandidatures = new ArrayList<>();
		List<Candidature> allCandidatures = candidatureDAO.findAll();
		OffreEmploi offreEmploi = offreEmploiDAO.findById(idOffreEmploi);

		if (offreEmploi != null) {
			for (Candidature candidature : allCandidatures) {
				if (offreEmploi.getNiveauQualificationBean().equals(candidature.getNiveauQualificationBean())) {
					for (SecteurActivite secteurActivite : candidature.getSecteurActivites()) {
						if (offreEmploi.getSecteurActivites().contains(secteurActivite)) {
							matchingCandidatures.add(candidature);
							break;
						}
					}
				}
			}
		}

		return matchingCandidatures;
	}

}
