package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.MessageCandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageCandidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

public class ServiceMessageCandidature implements IServiceMessageCandidature {

	@EJB
	private MessageCandidatureDAO messageCandidatureDAO;

	public ServiceMessageCandidature() {
		// Default constructor
	}
	
	@Override
	public MessageCandidature getMessageCandidature(int id) {
		return messageCandidatureDAO.findById(id);
	}

	@Override
	public List<MessageCandidature> listeDesMessagesCandidature() {
		return messageCandidatureDAO.findAll();
	}
	
	@Override
	public void ajoutMessageCandidature(OffreEmploi offreEmploiBean, Candidature candidature, String corpsMessage, Date dateEnvoi) {
		MessageCandidature messageCandidature = new MessageCandidature();
		messageCandidature.setOffreEmploiBean(offreEmploiBean);
		messageCandidature.setCandidatureBean(candidature);
		messageCandidature.setCorpsMessage(corpsMessage);
		messageCandidature.setDateEnvoi(dateEnvoi);
		
		messageCandidatureDAO.persist(messageCandidature);
	}

	@Override
	public void miseAJourMessageCandidature(String id, OffreEmploi offreEmploiBean, Candidature candidature, String corpsMessage, Date dateEnvoi) {
		MessageCandidature messageCandidature = messageCandidatureDAO.findById(Integer.parseInt(id));
		if (messageCandidature != null){
			messageCandidature.setOffreEmploiBean(offreEmploiBean);
			messageCandidature.setCandidatureBean(candidature);
			messageCandidature.setCorpsMessage(corpsMessage);
			messageCandidature.setDateEnvoi(dateEnvoi);
			messageCandidatureDAO.update(messageCandidature);
		}
		
	}

	@Override
	public void supprimerMessageCandidature(String id) {
		MessageCandidature messCand = messageCandidatureDAO.findById(Integer.parseInt(id));
		if (messCand != null){
			messageCandidatureDAO.remove(messCand);
		}	
	}
}
