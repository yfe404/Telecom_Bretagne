package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Session Bean implementation class OffreEmploiDAO
 */
@Stateless
@LocalBean
public class OffreEmploiDAO {

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
	public OffreEmploiDAO() {
		// TODO Auto-generated constructor stub
	}
	//-----------------------------------------------------------------------------
	public OffreEmploi findById(Integer id)
	{
		return entityManager.find(OffreEmploi.class, id);
	}
	//----------------------------------------------------------------------------
	public List<OffreEmploi> findAll()
	{
		Query query = entityManager.createQuery("select offre_emploi from OffreEmploi offre_emploi order by offre_emploi.id");
		List l = query.getResultList();

		return (List<OffreEmploi>) l;
	}
	//-----------------------------------------------------------------------------
	public List<OffreEmploi> findByEntreprise(Entreprise entreprise) {
		Query query = entityManager.createQuery("select offre_emploi from OffreEmploi offre_emploi order by offre_emploi.id where entreprise = ?");
		query.setParameter(1, entreprise.getId());
		List l = query.getResultList();

		return (List<OffreEmploi>) l;
	}
	//-----------------------------------------------------------------------------
		public List<OffreEmploi> findBy(SecteurActivite secteurActivite, NiveauQualification niveauQualification) {
			Query query = entityManager.createQuery("select offre_emploi from OffreEmploi offre_emploi order by offre_emploi.id where secteur_activite = ? and niveau_qualification = ?");
			query.setParameter(1, secteurActivite.getId());
			query.setParameter(2, niveauQualification.getId());
			List l = query.getResultList();

			return (List<OffreEmploi>) l;
		}
	//-----------------------------------------------------------------------------
	public void remove(OffreEmploi offreEmploi){
		entityManager.remove(offreEmploi);
	}
	//-----------------------------------------------------------------------------
	public OffreEmploi persist(OffreEmploi offreEmploi){
		entityManager.persist(offreEmploi);
		return offreEmploi;
	}
	//-----------------------------------------------------------------------------
	public OffreEmploi update(OffreEmploi offreEmploi){
		return entityManager.merge(offreEmploi);
	}
	//-----------------------------------------------------------------------------

}
