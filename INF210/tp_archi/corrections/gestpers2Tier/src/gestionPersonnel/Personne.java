package gestionPersonnel;

public class Personne {
	private short num;
	private String nom;
	private String prenom;
	private String telephone;
	private String fonction;
	private String service;

	public Personne(String num, String nom, String prenom, String telephone,
			String fonction, String service) {
		this.num = Short.parseShort(num);
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.fonction = fonction;
		this.service = service;
	}

	public String toHtml() {
		return "<tr><td>" + num + "</td><td>" + nom + "</td><td>" + prenom
				+ "</td>" + "<td>" + telephone + "</td><td>" + fonction
				+ "</td><td>" + service + "</td></tr>";
	}
}
