package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.EntrepriseDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;

/**
 * Session Bean implementation class ServiceEntreprise
 * 
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class ServiceEntreprise implements IServiceEntreprise
{
	//-----------------------------------------------------------------------------
	@EJB
	private EntrepriseDAO entrepriseDAO;
	//-----------------------------------------------------------------------------
	/**
	 * Default constructor.
	 */
	public ServiceEntreprise()
	{
		// TODO Auto-generated constructor stub
	}
	//-----------------------------------------------------------------------------
	@Override
	public Entreprise getEntreprise(int id)
	{
		return entrepriseDAO.findById(id);
	}
	//-----------------------------------------------------------------------------
	@Override
	public List<Entreprise> listeDesEntreprises()
	{
		return entrepriseDAO.findAll();
	}
	// -----------------------------------------------------------------------------
	@Override
	public void ajoutEntreprise(String nom, String descriptif, String adresse_postale )
	{
		Entreprise entreprise = new Entreprise(nom, descriptif, adresse_postale);
		entrepriseDAO.persist(entreprise);
	}
	// -----------------------------------------------------------------------------
	@Override
	public void miseAJourEntreprise(String id, String nom, String descriptif,
			String adresse_postale) {
		Entreprise ent = entrepriseDAO.findById(Integer.parseInt(id));
		if (ent != null){
			ent.setNom(nom);
			ent.setDescriptif(descriptif);
			ent.setAdressePostale(adresse_postale);
			entrepriseDAO.update(ent);
		}
	}
	@Override
	public void supprimerEntreprise(String id) {
		Entreprise ent = entrepriseDAO.findById(Integer.parseInt(id));
		if (ent != null){
			entrepriseDAO.remove(ent);
		}
	}
}
