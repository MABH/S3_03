package entitats;

import java.util.ArrayList;
import java.util.Scanner;

public class Floristeria {	
	private String nom;
	private ArrayList<Arbre> arbres;
	private ArrayList<Flor> flors;
	private ArrayList<Decoracio> decoracions;
	private ArrayList<Ticket> tickets;
	private Scanner sc = new Scanner(System.in);
	
	public Floristeria(String nom, ArrayList<Arbre> arbres, ArrayList<Flor> flors, ArrayList<Decoracio> decoracions,
			ArrayList<Ticket> tickets) {
		this.nom = nom;
		this.arbres = arbres;
		this.flors = flors;
		this.decoracions = decoracions;
		this.tickets = tickets;
	}
	
	public Floristeria() {
		
		System.out.print("\nIntrodueix el nom de la floristeria: ");
		this.nom = sc.nextLine();
		System.out.print("\n");
		
		CrearLlistaArbres();
		CrearLlistaFlors();
		CrearLlistaDecoracions();
		CrearTickets();
	}
	
	public Floristeria(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Arbre> getArbres() {
		return arbres;
	}	
	
	public void setArbres(Arbre arbre) {
		arbres.add(arbre);
	}
	
	public ArrayList<Flor> getFlors() {
		return flors;
	}
	
	/*public void setFlors(ArrayList<Flor> flors) {
		this.flors = flors;
	}*/
	
	public void setFlors(Flor flor) {
		flors.add(flor);
	}	
	
	public ArrayList<Decoracio> getDecoracions() {
		return decoracions;
	}
	
	/*public void setDecoracions(ArrayList<Decoracio> decoracions) {
		this.decoracions = decoracions;
	}*/
	
	public void setDecoracions(Decoracio decoracio) {
		decoracions.add(decoracio);
	}
		
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	
	public void setTickets(Ticket ticket) {
		this.tickets.add(ticket);
	}	
	
	public void CrearTickets() {
		this.tickets = new ArrayList<Ticket>();
	}
	
	private void CrearLlistaArbres(){
		arbres = new ArrayList<Arbre>();
		Arbre arbre = new Arbre();
		arbres.add(arbre);
	}
	
	private void CrearLlistaFlors(){
		flors = new ArrayList<Flor>();
		Flor flor = new Flor();
		flors.add(flor);
	}
	
	private void CrearLlistaDecoracions(){
		decoracions = new ArrayList<Decoracio>();
		Decoracio decoracio = new Decoracio();
		decoracions.add(decoracio);
	}
	
	public Arbre VendreArbre(String nom)
	{
		Arbre arbre = null;
		int i=0;
		
		while (arbre==null && i<arbres.size())
		{
			if (arbres.get(i).getNom().equals(nom)) arbre = arbres.get(i);
			i++;
		}
		
		if (arbre==null) System.out.println("L'arbre no existeix!");
		else this.getArbres().remove(i-1);
		return arbre;
	}
	
	public Flor VendreFlors(String nom)
	{
		Flor flor = null;
		int i=0;
		
		while (flor==null && i<flors.size())
		{
			if (flors.get(i).getNom().equals(nom)) flor = flors.get(i);
			i++;
		}
		
		if (flor==null) System.out.println("La flor no existeix!");
		else this.getFlors().remove(i-1);
		return flor;
	}
	
	public Decoracio VendreDecoracions(String nom)
	{
		Decoracio decoracio = null;
		int i=0;
		
		while (decoracio==null && i<decoracions.size())
		{
			if (decoracions.get(i).getNom().equals(nom)) decoracio = decoracions.get(i);
			i++;
		}
		
		if (decoracio==null) System.out.println("La decoracio no existeix!");
		else this.getDecoracions().remove(i-1);
		return decoracio;
	}
}
