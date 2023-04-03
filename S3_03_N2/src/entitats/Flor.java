package entitats;

public class Flor {	
	private String nom;
	private String color;
	private float preu;
	private int id;
	
	public Flor(String nom, String color, float preu, int id) {
		this.nom = nom;
		this.color = color;
		this.preu = preu;
		this.id = id;
	}
	
	public Flor() {		
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public float getPreu() {
		return preu;
	}
	public void setPreu(float preu) {
		this.preu = preu;
	}	
}
