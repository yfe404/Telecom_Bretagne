package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.MessageOffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageOffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

@Stateless
@LocalBean
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
	public List<MessageOffreEmploi> listeDesMessageOffreEmploiByCandidat(int idCandidat) {
		List<MessageOffreEmploi> messages = new ArrayList<>();

		for (MessageOffreEmploi message : messageOffreEmploiDAO.findAll()) {
			if (message.getCandidatureBean().getId().equals(idCandidat)) {
				messages.add(message);
			}
		}

		return messages;
	}

	@Override
	public List<MessageOffreEmploi> listeDesMessageOffreEmploiByEntreprise(int idEntreprise) {
		List<MessageOffreEmploi> messages = new ArrayList<>();

		for (MessageOffreEmploi message : messageOffreEmploiDAO.findAll()) {
			if (message.getOffreEmploiBean().getEntrepriseBean().getId().equals(idEntreprise)) {
				messages.add(message);
			}
		}

		return messages;
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
