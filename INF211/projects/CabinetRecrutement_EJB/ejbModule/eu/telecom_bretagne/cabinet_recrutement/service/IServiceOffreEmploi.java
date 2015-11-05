package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

@Remote
public interface IServiceOffreEmploi {

	OffreEmploi getOffreEmploi(int id);

	List<OffreEmploi> listeDesOffresEmploi();
	
	List<OffreEmploi> listeDesOffresEmploi(int idEntreprise);

	public void supprimerOffreEmploi(String id);
	
	public void ajoutOffreEmploi(String titre, String descriptif, String profilRecherche, Entreprise entreprise, Integer idNiveauQualification,
			String[] idSecteurActivites);

	public void miseAJourOffreEmploi(String id, String titre, String descriptif, String profilRecherche, Entreprise entreprise, Integer idNiveauQualification,
			String[] idSecteurActivites);
	
}