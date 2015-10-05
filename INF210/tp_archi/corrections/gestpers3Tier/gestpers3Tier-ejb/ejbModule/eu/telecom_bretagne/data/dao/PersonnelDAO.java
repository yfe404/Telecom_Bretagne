package eu.telecom_bretagne.data.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.data.model.Personne;
import eu.telecom_bretagne.data.model.Service;

/**
 * Session Bean implementation class PersonnelDAO
 */
@Stateless
public class PersonnelDAO implements IPersonnelDAOLocal {

	@PersistenceContext(unitName = "gestpers3Tier-ejbPU")
	private EntityManager em;
  
    public PersonnelDAO() {
    }

	@Override
	public void create(Personne personne) {
		if (personne == null) {
			return;
		}		
		em.persist(personne);
	}

	@Override
	public void update(Personne personne) {
		if (personne == null) {
			return;
		}

		em.merge(personne);
	}

//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Boolean remove(Personne p) {
		Personne persToRemove = findById(p.getId().longValue());
		try {
			em.remove(persToRemove);
		} catch (Exception pe) {
			System.err.println("Problem when deleting an entity ");
			return false;
		}
		return true;
	}

	@Override
	public List<Personne> findAll(String tri, String ordre) {
		String r = "select p from Personne p order by p." + tri;
		if (ordre.equals("asc")) {
			r += " ASC";
		} else {
			r += " DESC";
		}
		Query q = em.createQuery(r);
		return (List<Personne>) q.getResultList();
	}

	@Override
	public Personne findById(Long id) {
		Query q = em.createNamedQuery("Personne.findById");
		q.setParameter("id", id);
		return (Personne) q.getSingleResult();
	}

    @Override
    public List<Personne> findByService(Service s) {
        String request = "select p from Personne p where p.service=:service";
        
        Query query = em.createQuery(request);
        query.setParameter("service", s);
        return query.getResultList();
    }
    
	@Override
	public Boolean isEmpty() {
		if (em.createNamedQuery("Personne.findAll").getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}