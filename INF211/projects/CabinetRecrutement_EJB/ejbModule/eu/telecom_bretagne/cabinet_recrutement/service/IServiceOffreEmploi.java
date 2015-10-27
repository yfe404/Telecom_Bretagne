package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

@Remote
public interface IServiceOffreEmploi {

	OffreEmploi getOffreEmploi(int id);

	List<OffreEmploi> listeDesOffresEmploi();

	public void supprimerOffreEmploi(String id);
	
	public void ajoutOffreEmploi(String titre, String descriptif, String profilRecherche, Entreprise entreprise, NiveauQualification niveauQualificationBean,
			Set<SecteurActivite> secteurActivites);

	public void miseAJourOffreEmploi(String id, String titre, String descriptif, String profilRecherche, Entreprise entreprise, NiveauQualification niveauQualificationBean,
			Set<SecteurActivite> secteurActivites);
	
	
}