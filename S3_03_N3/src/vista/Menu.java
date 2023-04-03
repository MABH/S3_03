package vista;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import connexio.Connexio;
import entitats.ConsultesArbres;
import entitats.ConsultesDecoracions;
import entitats.ConsultesFloristeria;
import entitats.ConsultesFlors;
import entitats.ConsultesTicket;
import entitats.Floristeria;
import entitats.Ticket;

public class Menu {
	Scanner sc = new Scanner(System.in);	
	Connexio connexio=new Connexio();
	String nomFloristeria, nom, color, material;
	float alçada, preu;
	Date data;
	String codi;
	
	Floristeria floristeria=new Floristeria();
	/*Arbre arbre=null;
	Flor flor =null;
	Decoracio decoracio = null;*/
	ConsultesFloristeria cFloristeria;
	ConsultesArbres cArbres;
	ConsultesFlors cFlors;
	ConsultesDecoracions cDecos;
	ConsultesTicket cTicket;
	MongoDatabase mdb; 
	MongoCollection<Document> mdbCollection;
	Object floriId;
	
	public Menu() {		
		String opcio;
		mdb = connexio.getDb();
		cFloristeria = new ConsultesFloristeria(mdb);
		cArbres=new ConsultesArbres(mdb);
		cFlors=new ConsultesFlors(mdb);
		cDecos=new ConsultesDecoracions(mdb);
		cTicket = new ConsultesTicket(mdb);
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
			AfegirArbre(floriId);
			break;
		case "3":
			AfegirFlor(floriId);
			break;
		case "4":
			AfegirDeco(floriId);
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
	
	private void CarregarFloristeria() {
		System.out.print("Introdueixi la floristeria a carregar: ");
		nomFloristeria = sc.nextLine();
		System.out.print("");
		if (!cFloristeria.read(nomFloristeria))
		{
			System.out.println("La floristeria "+nomFloristeria+" no existeix.");
			nomFloristeria=null;
		}
		else {
			floriId=cFloristeria.readId(nomFloristeria);
			System.out.println("La floristeria "+nomFloristeria+" es correcte.");
		}
	}	
	
	private void CrearFloristeria() {
		System.out.print("Introdueixi el nom de la floristeria: ");
		nomFloristeria = sc.nextLine();
		System.out.print("");
		if (!cFloristeria.read(nomFloristeria))
		{
			cFloristeria.insert(nomFloristeria);
			floriId=cFloristeria.readId(nomFloristeria);
			System.out.println("La floristeria "+nomFloristeria+" es correcte.");
			System.out.println("Id: "+floriId);
		}
		else {
			System.out.println("La floristeria "+nomFloristeria+" ya existeix.");
		}
	}	
	
	private void AfegirArbre(Object floriId)
	{
		if (nomFloristeria!=null) {
			//cArbres=new ConsultesArbres(mdb);
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
			if(cArbres.read(nom, floriId))System.out.print("L'arbre "+nom+" ja existeix\n");
			else cArbres.insert(nom, alçada, preu, floriId);
		}
	}
	
	private void AfegirFlor(Object floriId)
	{
		if (nomFloristeria!=null) {
			//cFlors=new ConsultesFlors(mdb);
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
			if(cFlors.read(nom, floriId))System.out.print("La flor "+nom+" ja existeix\n");
			else cFlors.insert(nom, color, preu, floriId);
		}
	}
	
	private void AfegirDeco(Object floriId)
	{
		if (nomFloristeria!=null) {
			//cDecos=new ConsultesDecoracions(mdb);
			System.out.print("Introdueixi el nom de la decoracio: ");
			nom = sc.nextLine();
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
			if(cDecos.read(nom, floriId))System.out.print("La decoracio "+nom+" ja existeix\n");
			else cDecos.insert(nom, material, preu, floriId);
		}
	}
	
	private void Stock() {
		System.out.println("\nStock:");
		//cArbres=new ConsultesArbres(mdb);
		cArbres.readAll(floriId);
		//cFlors=new ConsultesFlors(mdb);
		cFlors.readAll(floriId);
		//cDecos=new ConsultesDecoracions(mdb);
		cDecos.readAll(floriId);
	}	
	
	private void Retirar(String entitat) {
		String nom;
		if (nomFloristeria!=null) {
			System.out.println("nomFloristeria: "+nomFloristeria+" flori_id: "+floriId);
			System.out.print("Introdueixi el nom per esborrar: ");
			nom = sc.nextLine();
			System.out.println("");
			switch (entitat) {
			case "ARBRE":
				//cArbres=new ConsultesArbres(mdb);
				cArbres.delete(nom, floriId);
				break;		
			case "FLOR":
				//cFlors=new ConsultesFlors(mdb);
				cFlors.delete(nom, floriId);
				break;		
			case "DECORACIO":
				//cDecos=new ConsultesDecoracions(mdb);
				cDecos.delete(nom, floriId);
				break;
			}
		}
	}
	
	private void Quantitats ()
	{
		System.out.println("Elements de la floristeria "+nomFloristeria);
		//cArbres=new ConsultesArbres(mdb);
		cArbres.count(floriId);
		//cFlors=new ConsultesFlors(mdb);
		cFlors.count(floriId);
		//cDecos=new ConsultesDecoracions(mdb);
		cDecos.count(floriId);
	}
	
	private void ValorTotal()
	{
		 System.out.print("Valor total d'arbres: ");	
		 //cArbres=new ConsultesArbres(mdb);
		 double totalArbres=cArbres.preuTotal(floriId);
		 System.out.println(totalArbres);
		 System.out.print("Valor total de flors: ");	
		 //cFlors=new ConsultesFlors(mdb);
		 double totalFlors=cFlors.preuTotal(floriId);
		 System.out.println(totalFlors);
		 System.out.print("Valor total de decoracions: ");	
		 //cDecos=new ConsultesDecoracions(mdb);
		 double totalDecos=cDecos.preuTotal(floriId);
		 System.out.println(totalDecos);
		 double total=totalDecos+totalFlors+totalArbres;
		 System.out.println("Valor total: "+total);
	}
	
	private void MenuTicket() {
		char opcio;
		//cTicket=new ConsultesTicket(mdb);
		cTicket.insert((String)codi, floriId);
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
		Object id;
		id=cTicket.readId(floriId, (String)codi);
		switch (opcio) {
			case '1':
				ComprarArbres(id);
			break;	
			case '2':
				ComprarFlors(id);
			break;
			case '3':
				ComprarDecoracions(id);
			break;	
			case '4':
				System.out.println("\nMenu principal");				
			break;
		}
		
	}
	
	private void ComprarArbres(Object idTicket) 
	{
		char resposta;
		String nom;
		int id=0;
		do {
			System.out.println("Introdueixi el nom de l'arbre: ");
			nom = sc.nextLine();
			//cArbres=new ConsultesArbres();
			if (cArbres.read(nom, floriId))
			{	
				cArbres.update(nom, floriId, idTicket);
			}
			else System.out.print("L'arbre "+nom+" no està disponible.");
			System.out.print("Vol comprar mes arbres (S/N)?");
			resposta = sc.nextLine().charAt(0);
		}while (resposta=='S'||resposta=='s');
	}
	
	private void ComprarDecoracions(Object idTicket) 
	{
		char resposta;
		String nom;
		int id=0;
		do {
			System.out.println("Introdueixi el nom de la decoracio: ");
			nom = sc.nextLine();
			if (cDecos.read(nom, floriId))
			{	
				cDecos.update(nom, floriId, idTicket);
			}
			else System.out.print("La decoracio "+nom+" no està disponible.");
			System.out.print("Vol comprar mes decoracions (S/N)?");
			resposta = sc.nextLine().charAt(0);
		}while (resposta=='S'||resposta=='s');
	}
	
	private void ComprarFlors(Object idTicket) 
	{
		char resposta;
		String nom;
		int id=0;
		do {
			System.out.println("Introdueixi el nom de la flor: ");
			nom = sc.nextLine();
			if (cFlors.read(nom, floriId))
			{	
				cFlors.update(nom, floriId, idTicket);
			}
			else System.out.print("La flor "+nom+" no està disponible.");
			System.out.print("Vol comprar mes flors (S/N)?");
			resposta = sc.nextLine().charAt(0);
		}while (resposta=='S'||resposta=='s');
	}
	
	private void CompresAntigues() {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		//Ticket ticket = new Ticket();
		int index = 0;		
		System.out.println("\nCompres Antigues: ");
		ticketList = cTicket.readAll(floriId);		
		
		while (ticketList.size()>0 && index<ticketList.size())
		{
			System.out.println("\nTicket: "+ticketList.get(index).getCodi());
			System.out.println("Vendes d'arbres:");
			cArbres.readVendes(ticketList.get(index).getId());
			System.out.println("\nVendes de flors:");
			cFlors.readVendes(ticketList.get(index).getId());
			System.out.println("\nVendes de decoracions:");
			cDecos.readVendes(ticketList.get(index).getId());
			index++;
		}
		
		if(ticketList==null)System.out.println("No hi han vendes a la florisateria "+nomFloristeria);
	};
	
	public void Facturacio() {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		int index = 0;		
		double totalArbres=0;
		double totalFlors=0;
		double totalDeco=0;
		double facturacio=0;
		System.out.println("\nFacturacio: ");
		ticketList = cTicket.readAll(floriId);
		while (ticketList.size()>0 && index<ticketList.size())
		{
			//cFlors.readVendes(ticketList.get(index).getId());
			System.out.println("\nTicket: "+ticketList.get(index).getCodi());			
			totalArbres=totalArbres+cArbres.readFacturacio(ticketList.get(index).getId());			
			totalFlors=totalFlors+cFlors.readFacturacio(ticketList.get(index).getId());			
			totalDeco=totalDeco+cDecos.readFacturacio(ticketList.get(index).getId());
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
}
