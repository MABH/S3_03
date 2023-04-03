package entitats;

public class Arbre {	
	private String nom;
	private float alçada;
	private float preu;
	private int id;
	
	public Arbre(String nom, float alçada, float preu, int id) {
		this.nom = nom;
		this.alçada = alçada;
		this.preu = preu;
		this.id = id;
	}
	
	public Arbre() {		
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
	public float getAlçada() {
		return alçada;
	}
	public void setAlçada(float alçada) {
		this.alçada = alçada;
	}
	public float getPreu() {
		return preu;
	}
	public void setPreu(float preu) {
		this.preu = preu;
	}	
}
