package eu.telecom_bretagne.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import eu.telecom_bretagne.data.dao.*;
import eu.telecom_bretagne.data.model.*;

/**
 * Session Bean implementation class ServicesBean
 */
@Stateless
public class ServicesBean implements IServicesBeanLocal, IServicesBeanRemote {
	@EJB
	private IServicesDAOLocal sDAO;
	@EJB
	private IPersonnelDAOLocal pDAO;

	/**
	 * Default constructor.
	 */
	public ServicesBean() {
	}

	@Override
	public List<Service> listeServices(String tri, String ordre) {
		return sDAO.findAll(tri, ordre);
	}

	@Override
	public void insertService(String nom) {
		Service service = new Service();
		service.setNom(nom);
		sDAO.create(service);

	}

	@Override
	public void closeService(Long id) {		
		Service s = sDAO.findById(id);

		// Look for employees in the department
		List<Personne> employees = pDAO.findByService(s);

		// Dismiss employees
		for (Personne p : employees) {
			pDAO.remove(p);
		}

		// Close the service
		sDAO.remove(s);

	}

	@Override
	public void closeService(String nom) {
		Service s = new Service();
		s.setNom(nom);
		s = sDAO.findByName(s);

		if (s != null) {// if the service exists
			// Look for employees in the department
			List<Personne> employees = pDAO.findByService(s);

			// Dismiss employees
			for (Personne p : employees) {
				pDAO.remove(p);
			}
			
			// Close the service
			sDAO.remove(s);
		}
	}
}
