package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.MessageOffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageOffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

public class ServiceMessageOffreEmploi implements IServiceMessageOffreEmploi {


	@EJB
	private MessageOffreEmploiDAO messageOffreEmploiDAO;

	public ServiceMessageOffreEmploi() {
		// Default constructor
	}


	@Override
	public MessageOffreEmploi getMessageOffreEmploi(int id) {
		return messageOffreEmploiDAO.findById(id);
	}

	@Override
	public List<MessageOffreEmploi> listeDesMessageOffreEmploi() {
		return messageOffreEmploiDAO.findAll(); 
	}

	@Override
	public void ajoutMessageOffreEmploi(OffreEmploi offreEmploiBean, Candidature candidature, String corpsMessage, Date dateEnvoi) {
		MessageOffreEmploi messageOffreEmploi = new MessageOffreEmploi();
		messageOffreEmploi.setOffreEmploiBean(offreEmploiBean);
		messageOffreEmploi.setCandidatureBean(candidature);
		messageOffreEmploi.setCorpsMessage(corpsMessage);
		messageOffreEmploi.setDateEnvoi(dateEnvoi);

		messageOffreEmploiDAO.persist(messageOffreEmploi);
	}

	@Override
	public void miseAJourMessageOffreEmploi(String id, OffreEmploi offreEmploiBean, Candidature candidature, String corpsMessage, Date dateEnvoi) {
		MessageOffreEmploi messageOffreEmploi = messageOffreEmploiDAO.findById(Integer.parseInt(id));
		if (messageOffreEmploi != null){
			messageOffreEmploi.setOffreEmploiBean(offreEmploiBean);
			messageOffreEmploi.setCandidatureBean(candidature);
			messageOffreEmploi.setCorpsMessage(corpsMessage);
			messageOffreEmploi.setDateEnvoi(dateEnvoi);
			
			messageOffreEmploiDAO.update(messageOffreEmploi);
		}

	}

	@Override
	public void supprimerMessageOffreEmploi(String id) {
		MessageOffreEmploi messOE = messageOffreEmploiDAO.findById(Integer.parseInt(id));
		if (messOE != null){
			messageOffreEmploiDAO.remove(messOE);
		}	
	}

}
