package eu.telecom_bretagne.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

import eu.telecom_bretagne.data.model.Service;

/**
 * Session Bean implementation class ServicesDAO
 */
@Stateless
@LocalBean
public class ServicesDAO implements IServicesDAOLocal {
	@PersistenceContext(unitName = "gestpers3Tier-ejbPU")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ServicesDAO() {
	}

	@Override
	public void create(Service service) {
		if (service == null) {
			return;
		}
		em.persist(service);
		//em.persist(new Service());
	}

	@Override
	public void update(Service service) {
		if (service == null) {
			return;
		}

		em.merge(service);
	}

	@Override
	public Boolean remove(Service s) {
		Service servToRemove = findById(s.getId().longValue());
		try {
			em.remove(servToRemove);
		} catch (Exception pe) {
			System.err.println("Problem when deleting an entity ");
			return false;
		}
		return true;
	}

	@Override
	public List<Service> findAll(String tri, String ordre) {
		String r = "select s from Service s order by s." + tri;
		if (ordre.equals("asc")) {
			r += " ASC";
		} else {
			r += " DESC";
		}
		Query q = em.createQuery(r);
		return (List<Service>) q.getResultList();
	}

	@Override
	public Service findById(Long id) {
		Query q = em.createNamedQuery("Service.findById");
		q.setParameter("id", id);
		return (Service) q.getSingleResult();
	}

	   @Override
	    public Service findByName(Service service) {
	        String req = "select s from Service s where s.nom=:nom";
	        Query q = em.createQuery(req);
	        q.setParameter("nom", service.getNom());

	        try {
	            Service res = (Service) q.getSingleResult();
	            return res;
	        } catch (NoResultException ex) {
	            return null;
	        }
	    }
	   
	@Override
	public Boolean isEmpty() {
		if (em.createNamedQuery("Service.findAll").getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}