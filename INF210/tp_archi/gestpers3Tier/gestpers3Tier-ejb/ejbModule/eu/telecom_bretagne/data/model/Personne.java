package eu.telecom_bretagne.data.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the personnes database table.
 * 
 */
@Entity
@Table(name="personnes")
@NamedQueries({
    @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p"),
    @NamedQuery(name = "Personne.findById", query = "SELECT p FROM Personne p WHERE p.id = :id")})
public class Personne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PERSONNES_ID_GENERATOR", sequenceName = "PERSONNES_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PERSONNES_ID_GENERATOR")
	private Integer id;

	private String fonction;

	private String nom;

	private String prenom;

	private String telephone;

	//bi-directional many-to-one association to Service
    @ManyToOne
	@JoinColumn(name="app_serv")
	private Service service;

    public Personne() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFonction() {
		return this.fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Personne)) {
			return false;
		}
		Personne other = (Personne) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "donnees.model.Personne[ id=" + id + " ]";
	}

	public String toHtml() {
		return "<tr><td>" + id + "</td><td>" + nom + "</td><td>" + prenom
				+ "</td>" + "<td>" + telephone + "</td><td>" + fonction
				+ "</td><td>" + service.getNom() + "</td></tr>";
	}
}