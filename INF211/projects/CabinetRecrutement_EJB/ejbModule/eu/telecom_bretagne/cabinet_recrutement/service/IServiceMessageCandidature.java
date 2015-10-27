package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageCandidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

@Remote
public interface IServiceMessageCandidature {

	public MessageCandidature getMessageCandidature(int id);

	public List<MessageCandidature> listeDesMessagesCandidature();
	
	public List<MessageCandidature> listeDesMessagesCandidatureByCandidat(int idCandidat);
	
	public List<MessageCandidature> listeDesMessagesCandidatureByEntreprise(int idEntreprise);

	public void ajoutMessageCandidature(OffreEmploi offreEmploiBean, Candidature candidature, String corpsMessage, Date dateEnvoi);

	public void miseAJourMessageCandidature(String id, OffreEmploi offreEmploiBean, Candidature candidature, String corpsMessage, Date dateEnvoi);

	public void supprimerMessageCandidature(String id);
	
}
