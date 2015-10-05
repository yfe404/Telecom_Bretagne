package eu.telecom_bretagne.data.dao;

import java.util.List;

import javax.ejb.Local;

import eu.telecom_bretagne.data.model.Service;

@Local
public interface IServicesDAOLocal {

	void create(Service service);

	void update(Service service);

	Boolean remove(Service s);

	List<Service> findAll(String tri, String ordre);

	Service findById(Long id);

	Boolean isEmpty();

	Service findByName(Service service);

}
