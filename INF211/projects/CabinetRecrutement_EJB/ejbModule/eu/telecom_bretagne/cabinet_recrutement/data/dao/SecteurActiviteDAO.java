package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Session Bean implementation class SecteurActiviteDAO
 */
@Stateless
@LocalBean
public class SecteurActiviteDAO {

	//-----------------------------------------------------------------------------
	/**
	 * Référence vers le gestionnaire de persistance.
	 */
	@PersistenceContext
	EntityManager entityManager;
	//-----------------------------------------------------------------------------
	/**
	 * Default constructor. 
	 */
	public SecteurActiviteDAO() {}
	//-----------------------------------------------------------------------------
	public SecteurActivite findById(Integer id)
	{
		return entityManager.find(SecteurActivite.class, id);
	}
	//----------------------------------------------------------------------------
	public List<SecteurActivite> findAll()
	{
		Query query = entityManager.createNativeQuery("SecteurActivite.findAll", SecteurActivite.class);

		@SuppressWarnings("unchecked")
		List<SecteurActivite> l = query.getResultList();

		return l;
	}
	//-----------------------------------------------------------------------------
	public SecteurActivite persist(SecteurActivite secteurActivite){
		entityManager.persist(secteurActivite);
		return secteurActivite;
	}
	//-----------------------------------------------------------------------------
	public SecteurActivite update(SecteurActivite secteurActivite){
		return entityManager.merge(secteurActivite);
	}
	//-----------------------------------------------------------------------------

}
