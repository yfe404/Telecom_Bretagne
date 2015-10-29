package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageOffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

@Remote
public interface IServiceMessageOffreEmploi {

	public MessageOffreEmploi getMessageOffreEmploi(int id);

	public List<MessageOffreEmploi> listeDesMessageOffreEmploi();
	
	public List<MessageOffreEmploi> listeDesMessageOffreEmploiByCandidat(int idCandidat);
	
	public List<MessageOffreEmploi> listeDesMessageOffreEmploiByEntreprise(int idEntreprise);

	public void ajoutMessageOffreEmploi(OffreEmploi offreEmploiBean, Candidature candidature, String corpsMessage, Date dateEnvoi);

	public void miseAJourMessageOffreEmploi(String id, OffreEmploi offreEmploiBean, Candidature candidature, String corpsMessage, Date dateEnvoi);

	public void supprimerMessageOffreEmploi(String id);
	
}
