package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the offre_emploi database table.
 * 
 */
@Entity
@Table(name="offre_emploi")
public class OffreEmploi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OFFRE_EMPLOI_ID_GENERATOR", sequenceName="OFFRE_EMPLOI_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OFFRE_EMPLOI_ID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_depot")
	private Date dateDepot;

	@Column(name="description_mission")
	private String descriptionMission;

	@Column(name="profil_recherche")
	private String profilRecherche;

	private String titre;

	//bi-directional many-to-one association to MessageCandidature
	@OneToMany(fetch = FetchType.EAGER, mappedBy="offreEmploiBean")
	private Set<MessageCandidature> messageCandidatures;

	//bi-directional many-to-one association to MessageOffreEmploi
	@OneToMany(fetch = FetchType.EAGER, mappedBy="offreEmploiBean")
	private Set<MessageOffreEmploi> messageOffreEmplois;

	//bi-directional many-to-one association to Entreprise
	@ManyToOne
	@JoinColumn(name="entreprise")
	private Entreprise entrepriseBean;

	//bi-directional many-to-one association to NiveauQualification
	@ManyToOne
	@JoinColumn(name="niveau_qualification")
	private NiveauQualification niveauQualificationBean;

	//bi-directional many-to-many association to SecteurActivite
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="offreEmplois")
	private Set<SecteurActivite> secteurActivites;

	public OffreEmploi() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateDepot() {
		return this.dateDepot;
	}

	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}

	public String getDescriptionMission() {
		return this.descriptionMission;
	}

	public void setDescriptionMission(String descriptionMission) {
		this.descriptionMission = descriptionMission;
	}

	public String getProfilRecherche() {
		return this.profilRecherche;
	}

	public void setProfilRecherche(String profilRecherche) {
		this.profilRecherche = profilRecherche;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Set<MessageCandidature> getMessageCandidatures() {
		return this.messageCandidatures;
	}

	public void setMessageCandidatures(Set<MessageCandidature> messageCandidatures) {
		this.messageCandidatures = messageCandidatures;
	}

	public MessageCandidature addMessageCandidature(MessageCandidature messageCandidature) {
		getMessageCandidatures().add(messageCandidature);
		messageCandidature.setOffreEmploiBean(this);

		return messageCandidature;
	}

	public MessageCandidature removeMessageCandidature(MessageCandidature messageCandidature) {
		getMessageCandidatures().remove(messageCandidature);
		messageCandidature.setOffreEmploiBean(null);

		return messageCandidature;
	}

	public Set<MessageOffreEmploi> getMessageOffreEmplois() {
		return this.messageOffreEmplois;
	}

	public void setMessageOffreEmplois(Set<MessageOffreEmploi> messageOffreEmplois) {
		this.messageOffreEmplois = messageOffreEmplois;
	}

	public MessageOffreEmploi addMessageOffreEmploi(MessageOffreEmploi messageOffreEmploi) {
		getMessageOffreEmplois().add(messageOffreEmploi);
		messageOffreEmploi.setOffreEmploiBean(this);

		return messageOffreEmploi;
	}

	public MessageOffreEmploi removeMessageOffreEmploi(MessageOffreEmploi messageOffreEmploi) {
		getMessageOffreEmplois().remove(messageOffreEmploi);
		messageOffreEmploi.setOffreEmploiBean(null);

		return messageOffreEmploi;
	}

	public Entreprise getEntrepriseBean() {
		return this.entrepriseBean;
	}

	public void setEntrepriseBean(Entreprise entrepriseBean) {
		this.entrepriseBean = entrepriseBean;
	}

	public NiveauQualification getNiveauQualificationBean() {
		return this.niveauQualificationBean;
	}

	public void setNiveauQualificationBean(NiveauQualification niveauQualificationBean) {
		this.niveauQualificationBean = niveauQualificationBean;
	}

	public Set<SecteurActivite> getSecteurActivites() {
		return this.secteurActivites;
	}

	public void setSecteurActivites(Set<SecteurActivite> secteurActivites) {
		this.secteurActivites = secteurActivites;
	}

}