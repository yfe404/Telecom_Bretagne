package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

/**
 * Session Bean implementation class OffreEmploiDAO
 */
@Stateless
@LocalBean
public class OffreEmploiDAO {

	@PersistenceContext
	EntityManager entityManager;

	public OffreEmploiDAO() {}

	public List<OffreEmploi> findAll() {
		final Query query = entityManager
				.createQuery("select offre_emploi from OffreEmploi offre_emploi order by offre_emploi.id");

		@SuppressWarnings("unchecked")
		final List<OffreEmploi> l = query.getResultList();

		return l;
	}

	public List<OffreEmploi> findByEntreprise(int idEntreprise) {
		final Query query = entityManager
				.createQuery("select offre_emploi from OffreEmploi offre_emploi"
						+ " where ofrreEmploi.entreprise.id = :idE order by offre_emploi.id");

		query.setParameter("idE", idEntreprise);

		@SuppressWarnings("unchecked")
		final List<OffreEmploi> l = query.getResultList();

		return l;
	}

	public OffreEmploi findById(Integer id) {
		return entityManager.find(OffreEmploi.class, id);
	}

	public List<OffreEmploi> findBySecteurActiviteAndNiveauQualification(int idSecteurActivite, int idNiveauQualification) {
		final Query query = entityManager.createQuery(
				"select offre_emploi from OffreEmploi offre_emploi" + " join offre_emploi.secteurActivites secteur"
						+ " where secteur.id = :idSA and offre_emploi.niveauQualification.id = :idNQ"
						+ " order by offre_emploi.id desc");

		query.setParameter("idSA", idSecteurActivite);
		query.setParameter("idNQ", idNiveauQualification);

		@SuppressWarnings("unchecked")
		final List<OffreEmploi> l = query.getResultList();

		return l;
	}

	public OffreEmploi persist(OffreEmploi offreEmploi) {
		entityManager.persist(offreEmploi);
		return offreEmploi;
	}

	public OffreEmploi update(OffreEmploi offreEmploi) {
		return entityManager.merge(offreEmploi);
	}

	public void remove(OffreEmploi offreEmploi) {
		if (!entityManager.contains(offreEmploi)) {
			offreEmploi = entityManager.merge(offreEmploi);
		}
		entityManager.remove(offreEmploi);
	}

}
