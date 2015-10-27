package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

@Remote
public interface IServiceSecteurActivite {

	SecteurActivite getSecteurActivite(int id);

	List<SecteurActivite> listeDesSecteursActivite();

}
