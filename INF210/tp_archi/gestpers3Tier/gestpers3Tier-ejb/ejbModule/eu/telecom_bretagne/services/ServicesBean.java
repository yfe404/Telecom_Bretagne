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
public class ServicesBean implements IServicesBeanLocal {
	@EJB
	private IServicesDAOLocal sDAO;

	/**
	 * Default constructor.
	 */
	public ServicesBean() {
	}

	@Override
	public List<Service> listeServices(String tri, String ordre) {
		return sDAO.findAll(tri, ordre);
	}
}
