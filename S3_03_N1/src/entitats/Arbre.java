package entitats;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Arbre {	
	private String nom;
	private float alçada;
	private float preu;
	private Scanner sc = new Scanner(System.in);
	
	public Arbre(String nom, float alçada, float preu) {
		this.nom = nom;
		this.alçada = alçada;
		this.preu = preu;
	}
	
	public Arbre() {
		System.out.println("\nNou arbre:");
		System.out.print("Nom: "); nom = sc.nextLine();
		System.out.print("\nAlçada: "); alçada = sc.nextFloat();
		sc.nextLine();
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

	@Override
	public String toString() {
		return "Arbre - nom: " + nom + ", alçada: " + alçada + ", preu: " + preu + "\n";
	}	
	
	public Arbre ParsearArbre(String linies) {
		String [] aux;
		StringTokenizer tokens;
		String seguentParaula;
		String nom, alçada, preu;
		
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
		alçada = aux[0];
		seguentParaula = tokens.nextToken();
		seguentParaula = tokens.nextToken();
		aux=seguentParaula.split(",");
		preu = aux[0];
		
		this.nom = nom;
		this.alçada = Float.parseFloat(alçada);
		this.preu = Float.parseFloat(preu);
		
		return this;
	}
}
