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
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class EntrepriseDAO {

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
	public EntrepriseDAO() {}
	//-----------------------------------------------------------------------------
	public Entreprise findById(Integer id)
	{
		return entityManager.find(Entreprise.class, id);
	}
	//----------------------------------------------------------------------------
	public List<Entreprise> findAll()
	{
		Query query = entityManager.createNativeQuery("Entreprise.findAll", Entreprise.class);

		@SuppressWarnings("unchecked")
		List<Entreprise> l = query.getResultList();

		return l;
	}
	//-----------------------------------------------------------------------------
	public void remove(Entreprise entreprise){
		entityManager.remove(entreprise);
	}
	//-----------------------------------------------------------------------------
	public Entreprise persist(Entreprise entreprise){
		entityManager.persist(entreprise);
		return entreprise;
	}
	//-----------------------------------------------------------------------------
	public Entreprise update(Entreprise entreprise){
		return entityManager.merge(entreprise);
	}
	//-----------------------------------------------------------------------------

}
