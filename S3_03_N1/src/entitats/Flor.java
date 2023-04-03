package entitats;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Flor {	
	private String nom;
	private String color;
	private float preu;
	private Scanner sc = new Scanner(System.in);
	
	public Flor(String nom, String color, float preu) {
		this.nom = nom;
		this.color = color;
		this.preu = preu;
	}
	
	public Flor() {
		System.out.println("\nNova flor:");
		System.out.print("Nom: "); nom = sc.nextLine();
		System.out.print("\nColor: "); color = sc.nextLine();
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

	@Override
	public String toString() {
		return "Flor - nom: " + nom + ", color: " + color + ", preu: " + preu + "\n";
	}
	
	public Flor ParsearFlor(String linies) {
		String [] aux;
		StringTokenizer tokens;
		String seguentParaula;
		String nom, color, preu;
		
		tokens = new StringTokenizer(linies);
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		aux=seguentParaula.split(",");
		nom = aux[0];
		seguentParaula = tokens.nextToken();
		aux=seguentParaula.split(",");
		color = aux[0];
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		aux=seguentParaula.split(",");
		preu = aux[0];		
		
		this.nom = nom;
		this.color = color;
		this.preu = Float.parseFloat(preu);
		
		return this;
	}
}
