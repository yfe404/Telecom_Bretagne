package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Session Bean implementation class CandidatureDAO
 */
@Stateless
@LocalBean
public class CandidatureDAO {

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
	public CandidatureDAO() {
		// TODO Auto-generated constructor stub
	}
	//-----------------------------------------------------------------------------
	public Candidature findById(Integer id)
	{
		return entityManager.find(Candidature.class, id);
	}
	//----------------------------------------------------------------------------
	public List<Candidature> findAll()
	{
		Query query = entityManager.createQuery("select candidature from Candidature candidature order by candidature.id");
		List l = query.getResultList();

		return (List<Candidature>) l;
	}
	//-----------------------------------------------------------------------------
	public List<Candidature> findBy(SecteurActivite secteurActivite, NiveauQualification niveauQualification) {
		Query query = entityManager.createQuery("select candidature from Candidature candidature order by candidature.id where secteur_activite = ? and niveau_qualification = ?");
		query.setParameter(1, secteurActivite.getId());
		query.setParameter(2, niveauQualification.getId());
		List l = query.getResultList();

		return (List<Candidature>) l;
	}

	//-----------------------------------------------------------------------------
	public void remove(Candidature candidature){
		entityManager.remove(candidature);
	}
	//-----------------------------------------------------------------------------
	public Candidature persist(Candidature candidature){
		entityManager.persist(candidature);
		return candidature;
	}
	//-----------------------------------------------------------------------------
	public Candidature update(Candidature candidature){
		return entityManager.merge(candidature);
	}
	//-----------------------------------------------------------------------------

}
