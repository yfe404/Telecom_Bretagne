package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageOffreEmploi;



/**
 * Session Bean implementation class MessageOffreEmploiDAO
 */
@Stateless
@LocalBean
public class MessageOffreEmploiDAO {

	@PersistenceContext
	EntityManager entityManager;

	public MessageOffreEmploiDAO() {}
	
	public List<MessageOffreEmploi> findAll() {
		final Query query = entityManager
				.createQuery("select message_offre_emploi from MessageOffreEmploi message_offre_emploi order by message_offre_emploi.id");

		@SuppressWarnings("unchecked")
		final List<MessageOffreEmploi> l = query.getResultList();

		return l;
	}
	
	public MessageOffreEmploi findById(Integer id) {
		return entityManager.find(MessageOffreEmploi.class, id);
	}
	
	public MessageOffreEmploi persist(MessageOffreEmploi messageOffreEmploi) {
		entityManager.persist(messageOffreEmploi);
		return messageOffreEmploi;
	}
	
	public MessageOffreEmploi update(MessageOffreEmploi messageOffreEmploi) {
		return entityManager.merge(messageOffreEmploi);
	}
	
	public void remove(MessageOffreEmploi messageOffreEmploi) {
		if (!entityManager.contains(messageOffreEmploi)) {
			messageOffreEmploi = entityManager.merge(messageOffreEmploi);
		}
		entityManager.remove(messageOffreEmploi);
	}

}

