package eu.telecom_bretagne.services;

import java.util.List;

import javax.ejb.Local;

import eu.telecom_bretagne.data.model.Service;

@Local
public interface IServicesBeanLocal {

	List<Service> listeServices(String tri, String ordre);

	void insertService(String nom);

    void closeService(Long id);

    void closeService(String nom);
}
