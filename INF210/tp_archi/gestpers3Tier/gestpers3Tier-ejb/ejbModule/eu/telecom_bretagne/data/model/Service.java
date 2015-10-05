package eu.telecom_bretagne.data.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;

/**
 * The persistent class for the services database table.
 * 
 */
@Entity
@Table(name = "services")
@NamedQueries({
		@NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
		@NamedQuery(name = "Service.findById", query = "SELECT s FROM Service s WHERE s.id = :id") })
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SERVICES_ID_GENERATOR", sequenceName = "SERVICES_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICES_ID_GENERATOR")
	private Integer id;

	private String nom;

	// bi-directional many-to-one association to Personne
	@OneToMany(mappedBy = "service")
	private Set<Personne> personnes;

	public Service() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Personne> getPersonnes() {
		return this.personnes;
	}

	public void setPersonnes(Set<Personne> personnes) {
		this.personnes = personnes;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Service)) {
			return false;
		}
		Service other = (Service) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Service[ id=" + id + " nom=" + nom + "]";
	}

	public String toHtml() {
		return "<tr><td>" + id + "</td><td>" + nom + "</td><tr>";
	}
}