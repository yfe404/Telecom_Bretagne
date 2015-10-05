package eu.telecom_bretagne.data.dao;

import java.util.List;

import javax.ejb.Local;

import eu.telecom_bretagne.data.model.Personne;
import eu.telecom_bretagne.data.model.Service;

@Local
public interface IPersonnelDAOLocal {

	void create(Personne personne);

	void update(Personne personne);

	Boolean remove(Personne p);

	Boolean isEmpty();

	Personne findById(Long id);

	List<Personne> findAll(String tri, String ordre);

	List<Personne> findByService(Service s);

}
