package gestionServices;

public class Service {
	private Integer num;
	private String nom;

	public Service(String num, String nom) {
		this.num = Integer.parseInt(num);
		this.nom = nom;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String toHtml() {
		return "<tr><td>" + num + "</td><td>" + nom + "</td></tr>";
	}
}
