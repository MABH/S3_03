package vista;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import conexio.Connexio;
import entitats.Arbre;
import entitats.ConsultesArbres;
import entitats.ConsultesDecoracio;
import entitats.ConsultesFloristeria;
import entitats.ConsultesFlors;
import entitats.ConsultesTickets;
import entitats.Decoracio;
import entitats.Flor;
import entitats.Floristeria;
import entitats.Ticket;

public class Menu {
	Scanner sc = new Scanner(System.in);	
	Connexio connexio=new Connexio();
	Floristeria floristeria=new Floristeria();
	Arbre arbre=null;
	Flor flor =null;
	Decoracio decoracio = null;
	ConsultesFloristeria cFloristeria = new ConsultesFloristeria();
	ConsultesArbres cArbres;
	ConsultesFlors cFlors;
	ConsultesDecoracio cDeco;
	ConsultesTickets cTicket;
	String nomFloristeria, nom, color, material;
	float alçada, preu;
	Date data;
	String codi;
	
	public Menu() {		
		String opcio;
		
		do {
			System.out.println("\n0. Carregar floristeria");
			System.out.println("1. Crear Floristeria");
			System.out.println("2. Afegir Arbre");
			System.out.println("3. Afegir Flor");
			System.out.println("4. Afegir Decoració");
			System.out.println("5. Stock");
			System.out.println("6. Retirar Arbre");
			System.out.println("7. Retirar Flor");
			System.out.println("8. Retirar Decoracio");
			System.out.println("9. Stock Quantitats");
			System.out.println("10. Valor Total");
			System.out.println("11. Crear tickets de compra");
			System.out.println("12. Llista de compres antigues");
			System.out.println("13. Facturació");
			System.out.println("14. Sortir");
			opcio = sc.nextLine();
			
			ComprovarOpcio(opcio);
			
		}while(!opcio.equals("14"));		
		
	}
	
	public void ComprovarOpcio(String opcio) {
		switch (opcio) {
		case "0":
			CarregarFloristeria();
			break;
		case "1":
			CrearFloristeria();			
			break;
		case "2":
			AfegirArbre();
			break;
		case "3":
			AfegirFlor();
			break;
		case "4":
			AfergirDecoracio();
			break;
		case "5":
			if (nomFloristeria!=null) 
				Stock();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;		
		case "6":		
			if (nomFloristeria!=null) 
				Retirar("ARBRE");
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "7":
			if (nomFloristeria!=null) 
				Retirar("FLOR");
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "8":
			if (nomFloristeria!=null) 
				Retirar("DECORACIO");
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "9":
			if (nomFloristeria!=null) 
				Quantitats();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "10":
			if (nomFloristeria!=null)  
				ValorTotal();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "11":
			if (nomFloristeria!=null)  
			{
				data = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				codi = dateFormat.format(data);
				MenuTicket();
			}
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "12":
			if (nomFloristeria!=null) 
				CompresAntigues();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "13":
			if (nomFloristeria!=null) 
				Facturacio();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "14":		
			System.out.println("\nAdéu-siau\n");
			break;
		default: 
			System.out.println("\nOpció incorrecta\n");
		break;
		}
	}
	
	public void CarregarFloristeria() {			
		System.out.print("Introdueixi la floristeria a carregar: ");
		nomFloristeria = sc.nextLine();
		System.out.print("");
		floristeria.setNom(nomFloristeria);
		if (!cFloristeria.select(floristeria))
		{
			System.out.println("La floristeria "+nomFloristeria+" no existeix.");
			nomFloristeria=null;
		}
		else {
			System.out.println("La floristeria "+nomFloristeria+" es correcte.");
		}
	}	
	
	
	public void CrearFloristeria() {
		floristeria=new Floristeria();
		System.out.print("Introdueixi el nom de la floristeria: ");
		nomFloristeria = sc.nextLine();
		floristeria.setNom(nomFloristeria);
		System.out.print("");
		if (!cFloristeria.select(floristeria))
		{
			cFloristeria.insert(nomFloristeria);
			System.out.println("La floristeria "+nomFloristeria+" s'ha creat ham exit.");
			cFloristeria.select(floristeria);
		}
		else {
			System.out.println("La floristeria "+nomFloristeria+" ja existeix.");
			nomFloristeria=null;
		}
	}
	
	public void AfegirArbre() {		
		if (nomFloristeria!=null) {
			cArbres=new ConsultesArbres();
			System.out.print("Introdueixi el nom de l'arbre: ");
			nom = sc.nextLine();
			System.out.print("");
			System.out.print("Introdueixi l'alçada de l'arbre: ");
			alçada = sc.nextFloat();
			System.out.print("");
			sc.nextLine();
			System.out.print("Introdueixi el preu de l'arbre: ");
			preu = sc.nextFloat();
			System.out.print("");
			sc.nextLine();
			if (cArbres.select(nom, floristeria.getId()).getNom()==null)
			{
				cArbres.insert(nom ,alçada,preu, floristeria.getId());
				System.out.println("L'arbre "+nom+" s'ha creat ham exit.");
			}
			else {
				System.out.println("L'arbre "+nom+" ja existeix en aquesta floristeria.");
				nomFloristeria=null;
			}
		}
		else System.out.println("\nNo hi ha cap floristeria disponible.");
	}
	
	public void AfegirFlor() {
		if (nomFloristeria!=null) {
			cFlors=new ConsultesFlors();
			System.out.print("Introdueixi el nom de la flor: ");
			nom = sc.nextLine();
			System.out.print("");
			System.out.print("Introdueixi el color de la flor: ");
			color = sc.nextLine();
			System.out.print("");
			System.out.print("Introdueixi el preu de la flor: ");
			preu = sc.nextFloat();
			System.out.print("");
			sc.nextLine();
			flor = new Flor(nom, color, preu, floristeria.getId());
			if (cFlors.select(nom, floristeria.getId()).getNom()==null)
			{
				cFlors.insert(nom, color, preu, floristeria.getId());
				System.out.println("La flor "+nom+" s'ha creat ham exit.");
			}
			else {
				System.out.println("La flor "+nom+" ja existeix en aquesta floristeria.");
				nomFloristeria=null;
			}
		}
		else System.out.println("\nNo hi ha cap floristeria disponible.");
	}
	
	public void AfergirDecoracio() {
		if (nomFloristeria!=null) {
			cDeco=new ConsultesDecoracio();
			System.out.print("Introdueixi el nom de la decoració: ");
			nom = sc.nextLine();
			System.out.print("");
			do {
				System.out.print("Introdueixi el material de la decoracio (PLASTIC, FUSTA): ");
				material = sc.nextLine();
				if (!material.equalsIgnoreCase("PLASTIC")&&!material.equalsIgnoreCase("FUSTA"))
					System.out.println("Material incorrecte");
			}while (!material.equalsIgnoreCase("PLASTIC")&&!material.equalsIgnoreCase("FUSTA"));
			
			System.out.print("");
			System.out.print("Introdueixi el preu de la decoracio: ");
			preu = sc.nextFloat();
			System.out.print("");
			sc.nextLine();
			decoracio = new Decoracio(nom, material, preu, floristeria.getId());
			if (cDeco.select(nom, floristeria.getId()).getNom()==null)
			{
				cDeco.insert(nom, material, preu, floristeria.getId());
				System.out.println("La decoracio "+nom+" s'ha creat ham exit.");
			}
			else {
				System.out.println("La decoracio "+nom+" ja existeix en aquesta floristeria.");
				nomFloristeria=null;
			}
		}
		else System.out.println("\nNo hi ha cap floristeria disponible.");
	}
	
	public void Retirar(String entitat) {
		String nom;
		if (nomFloristeria!=null) {
			System.out.print("Introdueixi el nom del element a eliminar: ");
			nom = sc.nextLine();
			System.out.println("");
			
			switch (entitat) {
			case "ARBRE":
				cArbres=new ConsultesArbres();
				cArbres.delete(nom, floristeria.getId());
				break;		
			case "FLOR":
				cFlors=new ConsultesFlors();
				cFlors.delete(nom, floristeria.getId());
				break;		
			case "DECORACIO":
				cDeco=new ConsultesDecoracio();
				cDeco.delete(nom, floristeria.getId());
				break;
			}
		}
	}
	
	public void Stock() {
		System.out.println("\nStock:");
		System.out.println("Arbres:");		
		cArbres=new ConsultesArbres();
		cArbres.selectAll(floristeria.getId());
		System.out.println("Flors:");
		cFlors=new ConsultesFlors();
		cFlors.selectAll(floristeria.getId());		
		System.out.println("Decoracions:");
		cDeco=new ConsultesDecoracio();
		cDeco.selectAll(floristeria.getId());		
	}	
	
	public void Quantitats() {
		System.out.println("Quantitats:");
		cArbres=new ConsultesArbres();
		cArbres.selectCount(floristeria.getId());
		cFlors=new ConsultesFlors();
		cFlors.selectCount(floristeria.getId());
		cDeco=new ConsultesDecoracio();
		cDeco.selectCount(floristeria.getId());
	}
	
	public void ValorTotal() {
		float total=0;
		System.out.println("Valors Totals:");
		cArbres=new ConsultesArbres();
		total +=cArbres.selectTotal(floristeria.getId());
		cFlors=new ConsultesFlors();
		total +=cFlors.selectTotal(floristeria.getId());
		cDeco=new ConsultesDecoracio();
		total +=cDeco.selectTotal(floristeria.getId());
		System.out.println("Total: "+total);
	}	
	
	public void CompresAntigues() {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		int index = 0;
		cTicket=new ConsultesTickets();
		cArbres=new ConsultesArbres();
		cFlors=new ConsultesFlors();
		cDeco=new ConsultesDecoracio();
		System.out.println("\nCompres Antigues: ");
		ticketList = cTicket.selectAll(floristeria.getId());
		while (ticketList.size()>0 && index<ticketList.size())
		{
			System.out.println("\nTicket: "+ticketList.get(index).getCodi());
			System.out.println("Vendes de arbres:");
			cArbres.selectVendes(ticketList.get(index).getId());
			System.out.println("Vendes de flors:");
			cFlors.selectVendes(ticketList.get(index).getId());
			System.out.println("Vendes de decoracions:");
			cDeco.selectVendes(ticketList.get(index).getId());
			index++;
		}
	}
	
	public void Facturacio() {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		int index = 0;
		cTicket=new ConsultesTickets();
		cArbres=new ConsultesArbres();
		cFlors=new ConsultesFlors();
		cDeco=new ConsultesDecoracio();
		float totalArbres=0;
		float totalFlors=0;
		float totalDeco=0;
		float facturacio=0;
		System.out.println("\nFacturacio: ");
		ticketList = cTicket.selectAll(floristeria.getId());
		while (ticketList.size()>0 && index<ticketList.size())
		{
			System.out.println("\nTicket: "+ticketList.get(index).getCodi());			
			totalArbres=totalArbres+cArbres.selectFacturacio(ticketList.get(index).getId());			
			totalFlors=totalFlors+cFlors.selectFacturacio(ticketList.get(index).getId());			
			totalDeco=totalDeco+cDeco.selectFacturacio(ticketList.get(index).getId());
			System.out.println("Facturacio de arbres: "+totalArbres);
			System.out.println("Facturacio de flors: "+totalFlors);
			System.out.println("Facturacio de decoracions: "+totalDeco);
			facturacio = facturacio+totalArbres+totalFlors+totalDeco;
			totalArbres=0;
			totalFlors=0;
			totalDeco=0;
			index++;
		}
		
		System.out.println("Facturacio total: "+facturacio);
	}
	
	public void MenuTicket() {		
		char opcio;
		cTicket=new ConsultesTickets();
		cTicket.insert(codi, floristeria.getId());
		do {
			System.out.println("1. Comprar Arbre");
			System.out.println("2. Comprar Flor");
			System.out.println("3. Comprar Decoració");
			System.out.println("4. Tornar");
			opcio = sc.nextLine().charAt(0);			
			
			ComprovarOpcio(opcio);
			
		}while(opcio!='4');		
	}
	
	private void ComprovarOpcio(char opcio) {		
		
		switch (opcio) {
			case '1':
				ComprarArbres();
			break;	
			case '2':
				ComprarFlors();
			break;
			case '3':
				ComprarDecoracions();
			break;	
			case '4':
				System.out.println("\nMenu principal");				
			break;
		}
		
	}
	
	private void ComprarArbres() 
	{
		char resposta;
		String nom;
		int id=0;
		do {
			System.out.println("Introdueixi el nom de l'arbre: ");
			nom = sc.nextLine();
			cArbres=new ConsultesArbres();
			arbre=cArbres.select(nom, floristeria.getId());
			if (arbre!=null)
			{	
				id=cTicket.selectId(floristeria.getId(), codi);
				cArbres.update(arbre.getId(),id);
			}
			else System.out.print("L'arbre "+nom+" no està disponible.");
			System.out.print("Vol comprar mes arbres (S/N)?");
			resposta = sc.nextLine().charAt(0);
		}while (resposta=='S'||resposta=='s');
	}
	
	private void ComprarFlors() 
	{
		char resposta;
		String nom;
		int id=0;
		
		do {
			System.out.print("Introdueixi el nom de la flor: ");
			nom = sc.nextLine();
			cFlors=new ConsultesFlors();
			flor=cFlors.select(nom, floristeria.getId());
			if (flor!=null)
			{	
				id=cTicket.selectId(floristeria.getId(), codi);
				cFlors.update(flor.getId(),id);
			}
			else System.out.print("La flor "+nom+" no està disponible.");
			System.out.print("Vol comprar mes flors (S/N)?");
			resposta = sc.nextLine().charAt(0);
		}while (resposta=='S'||resposta=='s');
	}
	
	private void ComprarDecoracions() 
	{
		char resposta;
		String nom;
		int id=0;
		
		do {
			System.out.print("Introdueixi el nom de la decoració: ");
			nom = sc.nextLine();
			cDeco=new ConsultesDecoracio();
			decoracio=cDeco.select(nom, floristeria.getId());
			if (decoracio!=null)
			{	
				id=cTicket.selectId(floristeria.getId(), codi);
				cDeco.update(decoracio.getId(),id);
			}
			else System.out.print("La decoracio "+nom+" no està disponible.");
			System.out.print("Vol comprar mes decoracions (S/N)?");
			resposta = sc.nextLine().charAt(0);
		}while (resposta=='S'||resposta=='s');		
	}
	
		
	
	/*Crear Floristeria.
	Afegir Arbre.
	Afegir Flor.
	Afegir Decoració.
	Stock: Imprimeix per pantalla tots els arbres, flors i decoració que té la floristeria.
	Retirar arbre.
	Retirar flor.
	Retirar decoració.
	Printar per pantalla stock amb quantitats.
	Printar per pantalla valor total de la floristeria.
	Crear tickets de compra amb múltiples objectes.
	Mostrar una llista de compres antigues.
	Visualitzar el total de diners guanyats amb totes les vendes.*/
}