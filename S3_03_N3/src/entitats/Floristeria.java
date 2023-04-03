package entitats;


public class Floristeria {	
	private String nom;	
	private int id;
	
	public Floristeria(String nom, int id) {
		this.nom = nom;
		this.id = id;
	}
	
	public Floristeria() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}		
}
