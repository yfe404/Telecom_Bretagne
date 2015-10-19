package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualificationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteurActiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;



/**
 * Session Bean implementation class NiveauQualification and SecteurActivite
 * 
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class ServiceIndexation implements IServiceIndexation{
	
	@EJB
	private NiveauQualificationDAO niveauQualificationDAO;
	
	@EJB
	private SecteurActiviteDAO secteurActiviteDAO;
	
	
	@Override
	public List<SecteurActivite> listeDesSecteursActivite() {
		return secteurActiviteDAO.findAll();
	}

	@Override
	public List<NiveauQualification> listeDesNiveauxQualification() {
		return niveauQualificationDAO.findAll();
	}

}
