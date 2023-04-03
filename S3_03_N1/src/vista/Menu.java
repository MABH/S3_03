package vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

import entitats.Arbre;
import entitats.Decoracio;
import entitats.Flor;
import entitats.Floristeria;
import entitats.Ticket;
import repository.Conexio;

public class Menu {
	Scanner sc = new Scanner(System.in);
	Floristeria floristeria;
	Arbre arbre;
	Decoracio decoracio;
	Flor flor;
	Ticket ticket;
	Conexio conexio=new Conexio();;
	
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
			if (floristeria!=null)
				Stock();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;		
		case "6":		
			if (floristeria!=null)
				Retirar("ARBRE");
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "7":
			if (floristeria!=null) 
				Retirar("FLOR");
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "8":
			if (floristeria!=null) 
				Retirar("DECORACIO");
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "9":
			if (floristeria!=null) 
				Quantitats();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "10":
			if (floristeria!=null) 
				ValorTotal();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "11":
			if (floristeria!=null) 
				MenuTicket();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "12":
			if (floristeria!=null) 
				CompresAntigues();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "13":
			if (floristeria!=null) 
				Facturacio();
			else System.out.println("\nNo hi ha elements disponibles.");
			break;
		case "14":			
			if (floristeria!=null) 
			{
				System.out.print("\nVol guardar les dades?(S/N)\n");
				opcio=sc.nextLine();
				System.out.println("");
				if (opcio.equalsIgnoreCase("S"))
					GuardarCanvis();
			}
			System.out.println("\nAdéu-siau\n");
			break;
		default: 
			System.out.println("\nOpció incorrecta\n");
		break;
		}
	}
	
	public void CarregarFloristeria() {		
		String nom;
		
		conexio.VeureFloristeries();		
		System.out.print("Introdueixi la floristeria a carregar: ");
		nom = sc.nextLine();
		System.out.print("");
		try {			
			ParsearDades(conexio.LlegirDades(nom));
		} catch (IOException e) {
			System.out.println("La floristeria "+nom+" no existeix.");
			e.printStackTrace();
		}
	}
	
	private void ParsearDades(String dades) {
		int index=0;
		String [] linies = dades.split("\n+");
		String [] aux;
		StringTokenizer tokens;
		String seguentParaula;
		String nomF;
		
		ArrayList<Arbre> arbres = new ArrayList<Arbre>();
		ArrayList<Flor> flors = new ArrayList<Flor>();
		ArrayList<Decoracio> decoracions = new ArrayList<Decoracio>();
		
		
		aux = linies[index].split(": ");
		nomF = aux[1];
		System.out.println("Floristeria: "+nomF);
		index++;
		boolean isTicket = false;
		
		if (linies.length==1) {
			System.out.println("La floristeria esta buida");
			floristeria = new Floristeria(nomF, arbres, flors, decoracions, null);
			
		}
		else {
			do {			
				//System.out.println("linies["+index+"]: "+linies[index]);
				tokens = new StringTokenizer(linies[index]);
				seguentParaula = tokens.nextToken();
				if (seguentParaula.equals("Ticket:") &&!isTicket) {
					isTicket=true;				
					floristeria = new Floristeria(nomF, arbres, flors, decoracions, ParsearTicket(index, linies));
				}
				if(!isTicket)
				{
					if(seguentParaula.equals("Arbre")) {
						arbre = new Arbre("",0.0f,0.0f);
						arbre=arbre.ParsearArbre(linies[index]);
						arbres.add(arbre);
					}					
					else if(seguentParaula.equals("Flor")) {				
						flor = new Flor("","",0.0f);
						flor=flor.ParsearFlor(linies[index]);		
						flors.add(flor);
					}
					else if(seguentParaula.equals("Decoracio")) {
						decoracio = new Decoracio("",null,0.0f);
						decoracio.ParsearDecoracio(linies[index]);
						decoracions.add(decoracio);
					}
				}
				
				index++;
			}while (index<linies.length && !isTicket);	
		}	
	}
	
	public ArrayList<Ticket> ParsearTicket(int index, String[] linies) {
		String [] aux;
		StringTokenizer tokens;
		String seguentParaula;
		ArrayList<Object> vendes = null;
		ArrayList<Ticket> tickets = null;
		boolean isFirst = true;
		
		vendes = new ArrayList<Object>();
		ticket = new Ticket(vendes);
		tickets = new ArrayList<Ticket>();
		
		do {
			tokens = new StringTokenizer(linies[index]);
			seguentParaula = tokens.nextToken();
			if (seguentParaula.equals("Ticket:")) 
			{							
				if (!isFirst)
				{				
					tickets.add(ticket);
					vendes = new ArrayList<Object>();
					ticket = new Ticket(vendes);					
				}
				aux = linies[index].split(": "); 				
				ticket.setVendes(aux[1]);
				isFirst=false;
			}			
			else {
				ticket.ParsearVenda(linies[index]);
			}
			index++;
		}while(index<linies.length);
		tickets.add(ticket);
		

		/*for(Ticket t : tickets)
		{
			System.out.println(t.toString());
		}	*/	
		
		return tickets;
	}
	
	public void CrearFloristeria() {
		floristeria = new Floristeria();
		System.out.println("Floristeria creada");
	}
	
	public void AfegirArbre() {
		if (floristeria!=null) {
			arbre = new Arbre();
			floristeria.setArbres(arbre);
		}
		else System.out.println("\nNo hi ha cap floristeria disponible.");
	}
	
	public void AfegirFlor() {
		if (floristeria!=null) {
			flor = new Flor();
			floristeria.setFlors(flor);
		}
		else System.out.println("\nNo hi ha cap floristeria disponible.");
	}
	
	public void AfergirDecoracio() {
		if (floristeria!=null) {
			decoracio = new Decoracio();
			floristeria.setDecoracions(decoracio);
		}
		else System.out.println("\nNo hi ha cap floristeria disponible.");
	}
	
	public void Retirar(String entitat) {
		String nom;
		
		System.out.print("Introdueixi el nom del element a eliminar: ");
		nom = sc.nextLine();
		System.out.println("");
		
		switch (entitat) {
		case "ARBRE":
			Iterator<Arbre> it_arbre=floristeria.getArbres().iterator();
			Arbre aux_arbre;
			while (it_arbre.hasNext()) {
				aux_arbre = it_arbre.next();
				if(nom.equals(aux_arbre.getNom())) it_arbre.remove();
			}
			break;		
		case "FLOR":
			Iterator<Flor> it_flor=floristeria.getFlors().iterator();
			Flor aux_flor;
			while (it_flor.hasNext()) {
				aux_flor = it_flor.next();
				if(nom.equals(aux_flor.getNom())) it_flor.remove();
			}
			break;		
		case "DECORACIO":
			Iterator<Decoracio> it_decoracio=floristeria.getDecoracions().iterator();
			Decoracio aux_decoracio;
			while (it_decoracio.hasNext()) {
				aux_decoracio = it_decoracio.next();
				if(nom.equals(aux_decoracio.getNom())) it_decoracio.remove();
			}
			break;
		}
	}
	
	public void Stock() {
		System.out.println("\nStock:");
		for(Arbre a : floristeria.getArbres()) {
			System.out.print(a.toString());
		}
		System.out.println("");
		for(Flor f : floristeria.getFlors()) {
			System.out.print(f.toString());
		}
		System.out.println("");
		for(Decoracio d : floristeria.getDecoracions()) {
			System.out.print(d.toString());
		}
		System.out.println("");
	}	
	
	public void Quantitats() {
		System.out.println("Quantitats:");
		System.out.println("Hi han "+floristeria.getArbres().size()+" arbres");
		System.out.println("Hi han "+floristeria.getFlors().size()+" flors");
		System.out.println("Hi han "+floristeria.getDecoracions().size()+" decoracions");
	}
	
	public void ValorTotal() {
		float totalArbres=0;
		for(Arbre a : floristeria.getArbres()) {
			totalArbres+=a.getPreu();			
		}		
		float totalFlors=0;
		for(Flor f : floristeria.getFlors()) {
			totalFlors+=f.getPreu();
		}
		float totalDecoracions=0;
		for(Decoracio d : floristeria.getDecoracions()) {
			totalDecoracions+=d.getPreu();			
		}
		float valorTotal=totalDecoracions+totalFlors+totalArbres;
		System.out.println("Valor total del stock d'arbres: "+totalArbres);
		System.out.println("Valor total del stock de flors: "+totalFlors);
		System.out.println("Valor total del stock de decoracións: "+totalDecoracions);
		System.out.println("\nValor total del stock: "+valorTotal);
		System.out.println("\n");
	}	
	
	public void CompresAntigues() {
		System.out.println("\nCompres Antigues: ");
		if(floristeria.getTickets()!=null)
			for(Ticket t : floristeria.getTickets()) {
				for(Object o : t.getVendes()) {
					System.out.println(o.toString());
				}
			}
		else System.out.println("No hi ha vendes.");
	}
	
	public void Facturacio() {
		float totalTicket, total=0;
		System.out.println("\nFacturacio");	
		if(floristeria.getTickets()!=null) {
			for(Ticket t : floristeria.getTickets()) {		
				totalTicket = 0;
				for(Object o : t.getVendes()) {
					if (o instanceof Arbre) {
						arbre = (Arbre)o;
						totalTicket+=arbre.getPreu();
					}
					else if (o instanceof Flor) {
						flor = (Flor)o;
						totalTicket+=flor.getPreu();
					}
					else if (o instanceof Decoracio) {
						decoracio = (Decoracio)o;
						totalTicket+=decoracio.getPreu();
					}
				}
				total+=totalTicket;
				System.out.println("Facturacio del ticket: "+t.getCodi()+ " total: "+totalTicket);
			}
			System.out.println("Facturacio total: "+total);	
		}
		else System.out.println("\nNo hi ha vendes.");
	}
	
	public void MenuTicket() {		
		char opcio;
		ticket = new Ticket();
		
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
				System.out.println("Ticket: "+ticket.getVendesLenght()+" codi: "+ticket.getVendes().get(0));
				System.out.println("Article 1: "+ticket.getVendes().toString());
				
				if (ticket.getVendesLenght()>1) floristeria.setTickets(ticket);
				else System.out.println("No s'ha efectuat cap compra "+ticket.getVendesLenght());
			break;
		}
		
	}
	
	private void ComprarArbres() 
	{
		char resposta;
		String nom;
		Arbre arbre = null;
		
		do {
			System.out.print("Introdueixi el nom de l'arbre: ");
			nom = sc.nextLine();
			arbre = floristeria.VendreArbre(nom);
			if (arbre!=null)
				ticket.setVendes(arbre);
			else System.out.print("L'arbre "+nom+" no està disponible.");
			System.out.print("Vol comprar mes arbres (S/N)?");
			resposta = sc.nextLine().charAt(0);
		}while (resposta=='S'||resposta=='s');
	}
	
	private void ComprarFlors() 
	{
		char resposta;
		String nom;
		Flor flor = null;
		
		do {
			System.out.print("Introdueixi el nom de la flor: ");
			nom = sc.nextLine();
			flor = floristeria.VendreFlors(nom);
			if (flor!=null)
				ticket.setVendes(flor);
			else System.out.print("La flor "+nom+" no està disponible.");
			System.out.print("Vol comprar mes flors (S/N)?");
			resposta = sc.nextLine().charAt(0);
		}while (resposta=='S'||resposta=='s');
	}
	
	private void ComprarDecoracions() 
	{
		char resposta;
		String nom;
		Decoracio decoracio = null;
		
		do {
			System.out.print("Introdueixi el nom de la decoració: ");
			nom = sc.nextLine();
			decoracio = floristeria.VendreDecoracions(nom);
			if (decoracio!=null)
				ticket.setVendes(decoracio);
			else System.out.print("La decoracio "+nom+" no està disponible.");
			System.out.print("Vol comprar mes decoracions (S/N)?");
			resposta = sc.nextLine().charAt(0);
		}while (resposta=='S'||resposta=='s');		
	}
	
	private void GuardarCanvis(){
		conexio=new Conexio();
		String dades="Nom: "+floristeria.getNom()+"\n";
		
		if(floristeria.getArbres()!=null)
			for(Arbre a : floristeria.getArbres()) {
				dades=dades+a.toString()+"\n";
			}
		if(floristeria.getFlors()!=null)
			for(Flor f : floristeria.getFlors()) {
				dades=dades+f.toString()+"\n";
			}
		if(floristeria.getDecoracions()!=null)
			for(Decoracio d : floristeria.getDecoracions()) {
				dades=dades+d.toString()+"\n";
			}
		if(floristeria.getTickets()!=null)
			for(Ticket t : floristeria.getTickets()) {		
				dades=dades+t.toString();
			}
		System.out.print(dades);
		try {
			conexio.EscriuDades(dades, floristeria.getNom());
			System.out.println("Dades guardades correctament!");
		} catch (IOException e) {
			System.out.println("Error al guardar les dades.");
			e.printStackTrace();
		}
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