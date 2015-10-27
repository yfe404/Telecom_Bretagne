package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageCandidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

public interface IServiceMessageCandidature {

	public MessageCandidature getMessageCandidature(int id);

	public List<MessageCandidature> listeDesMessagesCandidature();

	public void ajoutMessageCandidature(OffreEmploi offreEmploiBean, String corpsMessage, Date dateEnvoi);

	public void miseAJourMessageCandidature(String id, OffreEmploi offreEmploiBean, String corpsMessage, Date dateEnvoi);

	public void supprimerMessageCandidature(String id);
	
}
