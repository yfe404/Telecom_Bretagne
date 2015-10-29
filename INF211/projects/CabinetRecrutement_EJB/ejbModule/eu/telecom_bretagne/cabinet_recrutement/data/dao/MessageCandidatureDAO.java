package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageCandidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;


/**
 * Session Bean implementation class NiveauQualificationDAO
 */
@Stateless
@LocalBean
public class MessageCandidatureDAO {

	@PersistenceContext
	EntityManager entityManager;

	public MessageCandidatureDAO() {}
	
	public List<MessageCandidature> findAll() {
		final Query query = entityManager
				.createQuery("select message_candidature from MessageCandidature message_candidature order by message_candidature.id");

		@SuppressWarnings("unchecked")
		final List<MessageCandidature> l = query.getResultList();

		return l;
	}
	
	public MessageCandidature findById(Integer id) {
		return entityManager.find(MessageCandidature.class, id);
	}
	
	public MessageCandidature persist(MessageCandidature messageCandidature) {
		entityManager.persist(messageCandidature);
		return messageCandidature;
	}
	
	public MessageCandidature update(MessageCandidature messageCandidature) {
		return entityManager.merge(messageCandidature);
	}
	
	public void remove(MessageCandidature messageCandidature) {
		if (!entityManager.contains(messageCandidature)) {
			messageCandidature = entityManager.merge(messageCandidature);
		}
		entityManager.remove(messageCandidature);
	}

}

