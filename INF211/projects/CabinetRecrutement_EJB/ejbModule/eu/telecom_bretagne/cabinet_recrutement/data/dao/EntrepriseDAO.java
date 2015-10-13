package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;

/**
 * Session Bean implementation class EntrepriseDAO
 * 
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class EntrepriseDAO {

	@PersistenceContext
	EntityManager entityManager;

	public EntrepriseDAO() {}

	public List<Entreprise> findAll() {
		final Query query = entityManager
				.createQuery("select entreprise from Entreprise entreprise order by entreprise.id");

		@SuppressWarnings("unchecked")
		final List<Entreprise> l = query.getResultList();

		return l;
	}
	
	public Entreprise findById(Integer id) {
		return entityManager.find(Entreprise.class, id);
	}

	public Entreprise persist(Entreprise entreprise) {
		entityManager.persist(entreprise);
		return entreprise;
	}
	
	public Entreprise update(Entreprise entreprise) {
		return entityManager.merge(entreprise);
	}
	
	public void remove(Entreprise entreprise) {
		if (!entityManager.contains(entreprise)) {
			entreprise = entityManager.merge(entreprise);
		}
		entityManager.remove(entreprise);
	}

}
