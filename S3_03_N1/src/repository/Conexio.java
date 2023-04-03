package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Conexio {

	private BufferedReader bufEntrada;
	private FileReader fluxeLect;
	private FileWriter fluxeEscr;
	private PrintWriter bufSortida;
	
	public Conexio(BufferedReader bufEntrada, FileReader fluxeLect, FileWriter fluxeEscr, PrintWriter bufSortida) {
		this.bufEntrada = bufEntrada;
		this.fluxeLect = fluxeLect;
		this.fluxeEscr = fluxeEscr;
		this.bufSortida = bufSortida;
	}
	
	public Conexio() {
		this.bufEntrada = null;
		this.fluxeLect = null;
		this.fluxeEscr = null;
		this.bufSortida = null;
	}
	
	public String LlegirDades(String nom) throws IOException {
		this.fluxeLect = new FileReader(nom+".floristeria");
		bufEntrada = new BufferedReader(fluxeLect);
		String dades="";
		String linea = this.bufEntrada.readLine();
		while(linea!=null) {
			dades+=linea+"\n";
			linea=this.bufEntrada.readLine();
		}
		bufEntrada.close();
		return dades;
	}
	
	public void EscriuDades(String dades, String nom) throws IOException {
		fluxeEscr = new FileWriter(nom+".floristeria");
		bufSortida = new PrintWriter(fluxeEscr);
		bufSortida.println(dades);
		bufSortida.close();		
	}
	
	public void VeureFloristeries()
	{
		File directorio = new File (".");
		String[] ficheros = directorio.list();
		if (ficheros == null)
			System.out.println("No hay ficheros en el directorio especificado");
		else { 
			System.out.println("Fitxers disponibles:");
			for (int x=0;x<ficheros.length;x++) 
				if(ficheros[x].endsWith(".floristeria"))
				System.out.println(ficheros[x]);
			}
	}
}
