package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;

/**
 * Session Bean implementation class CandidatureDAO
 */
@Stateless
@LocalBean
public class CandidatureDAO {

	@PersistenceContext
	EntityManager entityManager;

	public CandidatureDAO() {}

	public List<Candidature> findAll() {
		final Query query = entityManager
				.createQuery("select candidature from Candidature candidature order by candidature.id");

		@SuppressWarnings("unchecked")
		final List<Candidature> l = query.getResultList();

		return l;
	}
	
	public Candidature findById(Integer id) {
		return entityManager.find(Candidature.class, id);
	}
	
	public List<Candidature> findBySecteurActiviteAndNiveauQualificaion(int idSecteurActivite, int idNiveauQualification) {
		final Query query = entityManager
				.createQuery("select candidature from Candidature candidature"
						+ " join candidature.secteurActivites secteur"
						+ " where secteur.id = :idSA and candidature.niveauQualificationBean.id = :idNQ"
						+ " order by candidature.id desc");

		query.setParameter("idSA", idSecteurActivite);
		query.setParameter("idNQ", idNiveauQualification);

		@SuppressWarnings("unchecked")
		final List<Candidature> l = query.getResultList();

		return l;
	}

	public Candidature persist(Candidature candidature) {
		entityManager.persist(candidature);
		return candidature;
	}
	
	public Candidature update(Candidature candidature) {
		return entityManager.merge(candidature);
	}

	public void remove(Candidature candidature) {
		if (!entityManager.contains(candidature)) {
			candidature = entityManager.merge(candidature);
		}
		entityManager.remove(candidature);
	}

}
