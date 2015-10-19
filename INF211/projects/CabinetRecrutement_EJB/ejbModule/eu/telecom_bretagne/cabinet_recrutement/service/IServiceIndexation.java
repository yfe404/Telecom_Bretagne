package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

public interface IServiceIndexation {
	public List<SecteurActivite> listeDesSecteursActivite();
	public List<NiveauQualification> listeDesNiveauxQualification();
}
