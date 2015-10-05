package eu.telecom_bretagne.services;

import java.util.List;

import javax.ejb.Local;

import eu.telecom_bretagne.data.model.Personne;

@Local
public interface IPersonnelBeanLocal {

	List<Personne> annuaire(String tri, String ordre);

	void embauche(String nom, String prenom, String telephone, String fonction,
			String service);
}
