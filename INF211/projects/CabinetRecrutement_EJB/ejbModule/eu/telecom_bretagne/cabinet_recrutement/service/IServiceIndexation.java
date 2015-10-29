package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

@Remote
public interface IServiceIndexation {
	public List<SecteurActivite> listeDesSecteursActivite();
	public List<NiveauQualification> listeDesNiveauxQualification();
	SecteurActivite getSecteurActiviteById(int id);
	NiveauQualification getNiveauQualification(int id);
	List<Candidature> getMatchingCandidaturesForOffreEmploi(int idOffreEmploi);
}
