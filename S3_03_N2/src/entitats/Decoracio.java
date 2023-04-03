package entitats;

public class Decoracio {	
	public enum Material {FUSTA, PLASTIC};
	private String nom;
	//private Material material;
	private String material;
	private float preu;
	private int id;
	
	//public Decoracio(String nom, Material material, float preu, int id) {
	public Decoracio(String nom, String material, float preu, int id) {
		this.nom = nom;
		this.material = material;
		this.preu = preu;
		this.id = id;
	}
	
	public Decoracio() {
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
	public String getMaterial() {
		//return material.name();
		return material;
	}
	public void setMaterial(String material) {
		//this.material = Material.valueOf(material);
		this.material = material;
	}
	public float getPreu() {
		return preu;
	}
	public void setPreu(float preu) {
		this.preu = preu;
	}
	
}
