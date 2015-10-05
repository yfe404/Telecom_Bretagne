package eu.telecom_bretagne.services;

import java.util.List;

import javax.ejb.*;

import eu.telecom_bretagne.data.dao.*;
import eu.telecom_bretagne.data.model.*;

/**
 * Session Bean implementation class PersonnelBean
 */
@Stateless
public class PersonnelBean implements IPersonnelBeanRemote, IPersonnelBeanLocal {

	@EJB
	IPersonnelDAOLocal pDAO;
	@EJB
	IServicesDAOLocal sDAO;

	public PersonnelBean() {
	}

	@Override
	public List<Personne> annuaire(String tri, String ordre) {
		return pDAO.findAll(tri, ordre);
	}

	@Override
	public void embauche(String nom, String prenom, String telephone,
			String fonction, String service) {
		Personne p = new Personne();
		p.setNom(nom);
		p.setPrenom(prenom);
		p.setTelephone(telephone);
		p.setFonction(fonction);

		// Le service doit exister. On le recupère de la BD
		Service s = sDAO.findById(Long.parseLong(service));
		p.setService(s);

		pDAO.create(p);
	}
}
