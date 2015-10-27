package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageOffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

public interface IServiceMessageOffreEmploi {

	public MessageOffreEmploi getMessageOffreEmploi(int id);

	public List<MessageOffreEmploi> listeDesMessageOffreEmploi();

	public void ajoutMessageOffreEmploi(OffreEmploi offreEmploiBean, Candidature candidature, String corpsMessage, Date dateEnvoi);

	public void miseAJourMessageOffreEmploi(String id, OffreEmploi offreEmploiBean, Candidature candidature, String corpsMessage, Date dateEnvoi);

	public void supprimerMessageOffreEmploi(String id);
	
}
