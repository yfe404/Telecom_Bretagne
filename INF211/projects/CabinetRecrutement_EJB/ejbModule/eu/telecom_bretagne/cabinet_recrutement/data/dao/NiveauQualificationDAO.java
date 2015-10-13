package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;

/**
 * Session Bean implementation class NiveauQualificationDAO
 */
@Stateless
@LocalBean
public class NiveauQualificationDAO {

	@PersistenceContext
	EntityManager entityManager;

	public NiveauQualificationDAO() {}
	
	public List<NiveauQualification> findAll() {
		final Query query = entityManager
				.createQuery("select niveau_qualification from NiveauQualification niveau_qualification order by niveau_qualification.id");

		@SuppressWarnings("unchecked")
		final List<NiveauQualification> l = query.getResultList();

		return l;
	}
	
	public NiveauQualification findById(Integer id) {
		return entityManager.find(NiveauQualification.class, id);
	}
	
	public NiveauQualification persist(NiveauQualification niveauQualification) {
		entityManager.persist(niveauQualification);
		return niveauQualification;
	}
	
	public NiveauQualification update(NiveauQualification niveauQualification) {
		return entityManager.merge(niveauQualification);
	}

}
