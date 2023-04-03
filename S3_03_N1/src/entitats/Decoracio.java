package entitats;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Decoracio {	
	public enum Material {FUSTA, PLASTIC};
	private String nom;
	private Material material;
	private float preu;
	private Scanner sc = new Scanner(System.in);
	
	public Decoracio(String nom, Material material, float preu) {
		super();
		this.nom = nom;
		this.material = material;
		this.preu = preu;
	}
	
	public Decoracio() {
		System.out.println("\nNova decoracio:");
		System.out.print("Nom: "); nom = sc.nextLine();
		System.out.print("\nMaterial (FUSTA, PLASTIC): "); material = Material.valueOf(sc.nextLine().toUpperCase());
		System.out.print("Preu: "); preu = sc.nextFloat();
		sc.nextLine();
		System.out.print("\n");
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public float getPreu() {
		return preu;
	}
	public void setPreu(float preu) {
		this.preu = preu;
	}
	
	@Override
	public String toString() {
		return "Decoracio - nom: " + nom + ", material: " + material + ", preu: " + preu+"\n";
	}
	
	public Decoracio ParsearDecoracio(String linies) {
		String [] aux;
		StringTokenizer tokens;
		String seguentParaula;
		String nom, preu;
		Material material;
		
		tokens = new StringTokenizer(linies);
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		aux=seguentParaula.split(",");
		nom = aux[0];
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		aux=seguentParaula.split(",");
		material=Material.valueOf(aux[0]);
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		aux=seguentParaula.split(",");
		preu = aux[0];		
			
		this.nom = nom;
		this.material = material;
		this.preu = Float.parseFloat(preu);
		
		return this;
	}
}
