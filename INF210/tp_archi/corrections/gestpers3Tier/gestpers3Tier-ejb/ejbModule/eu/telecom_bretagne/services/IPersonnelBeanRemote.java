package eu.telecom_bretagne.services;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.data.model.Personne;

@Remote
public interface IPersonnelBeanRemote {

	List<Personne> annuaire(String tri, String ordre);

	void embauche(String nom, String prenom, String telephone, String fonction,
			String service);
}
