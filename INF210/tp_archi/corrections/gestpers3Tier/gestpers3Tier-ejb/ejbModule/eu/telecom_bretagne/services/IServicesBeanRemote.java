package eu.telecom_bretagne.services;

import java.util.List;

import eu.telecom_bretagne.data.model.Service;

public interface IServicesBeanRemote {

	List<Service> listeServices(String tri, String ordre);

	void insertService(String nom);

	void closeService(Long id);

	void closeService(String nom);
}
