package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Interface du service gérant les Candidature.
 * 
 * @author Luke Skywalker
 */
@Remote
public interface IServiceCandidature {
	// -----------------------------------------------------------------------------
	/**
	 * Obtention d'une candidature suivant son id.
	 * 
	 * @param id
	 *            id de la candidature.
	 * @return l'instance de la candidature.
	 */
	// -----------------------------------------------------------------------------
	public Candidature getCandidature(int id);

	/**
	 * Obtention de la liste de toutes les candidatures référencées dans le
	 * système.
	 * 
	 * @return la liste des candidatures dans une {@code List<Candidature>}.
	 */
	public List<Candidature> listeDesCandidatures();

	// -----------------------------------------------------------------------------
	public void ajoutCandidature(String adresseEmail, String adressePostale,
			String cv, Date dateDepot, Date dateNaissance, String nom,
			String prenom, NiveauQualification niveauQualificationBean,
			Set<SecteurActivite> secteurActivites);

	public void miseAJourCandidature(String id, String adresseEmail, String adressePostale,
			String cv, Date dateNaissance, String nom,
			String prenom, NiveauQualification niveauQualificationBean,
			Set<SecteurActivite> secteurActivites);

	public void supprimerCandidature(String id);
}
