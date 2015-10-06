package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the niveau_qualification database table.
 * 
 */
@Entity
@Table(name="niveau_qualification")
@NamedQuery(name="NiveauQualification.findAll", query="SELECT n FROM NiveauQualification n")
public class NiveauQualification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NIVEAU_QUALIFICATION_ID_GENERATOR", sequenceName="NIVEAU_QUALIFICATION_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NIVEAU_QUALIFICATION_ID_GENERATOR")
	private Integer id;

	private String intitule;

	//bi-directional many-to-one association to Candidature
	@OneToMany(mappedBy="niveauQualificationBean")
	private Set<Candidature> candidatures;

	//bi-directional many-to-one association to OffreEmploi
	@OneToMany(mappedBy="niveauQualificationBean")
	private Set<OffreEmploi> offreEmplois;

	public NiveauQualification() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Set<Candidature> getCandidatures() {
		return this.candidatures;
	}

	public void setCandidatures(Set<Candidature> candidatures) {
		this.candidatures = candidatures;
	}

	public Candidature addCandidature(Candidature candidature) {
		getCandidatures().add(candidature);
		candidature.setNiveauQualificationBean(this);

		return candidature;
	}

	public Candidature removeCandidature(Candidature candidature) {
		getCandidatures().remove(candidature);
		candidature.setNiveauQualificationBean(null);

		return candidature;
	}

	public Set<OffreEmploi> getOffreEmplois() {
		return this.offreEmplois;
	}

	public void setOffreEmplois(Set<OffreEmploi> offreEmplois) {
		this.offreEmplois = offreEmplois;
	}

	public OffreEmploi addOffreEmploi(OffreEmploi offreEmploi) {
		getOffreEmplois().add(offreEmploi);
		offreEmploi.setNiveauQualificationBean(this);

		return offreEmploi;
	}

	public OffreEmploi removeOffreEmploi(OffreEmploi offreEmploi) {
		getOffreEmplois().remove(offreEmploi);
		offreEmploi.setNiveauQualificationBean(null);

		return offreEmploi;
	}

}