package entitats;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class Ticket {
	private ArrayList<Object> vendes;
	private String codi;
	
	public Ticket() {		
		vendes = new ArrayList<Object>();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		codi = dateFormat.format(date);
		vendes.add(codi);
	}
	
	public Ticket(ArrayList<Object> vendes) {		
		this.vendes = vendes;		
	}
	
	public ArrayList<Object> getVendes() {
		return vendes;
	}
	public void setVendes(Object item) {			
		vendes.add(item);
	}	
	
	public int getVendesLenght() {
		return vendes.size();
	}
	
	public String getCodi() {
		return vendes.get(0).toString();
	}
	
	@Override
	public String toString() {
		String ticket="Ticket: "+ vendes.get(0);
		for(Object o : vendes) {	
			ticket=ticket+"\n";
			if (o instanceof Arbre) {
				Arbre arbre = (Arbre)o;
				ticket=ticket+arbre.toString();
			}
			else if (o instanceof Flor) {
				Flor flor = (Flor)o;
				ticket=ticket+flor.toString();
			}
			else if (o instanceof Decoracio) {
				Decoracio decoracio = (Decoracio)o;
				ticket=ticket+decoracio.toString();
			}
		}
		return ticket+"\n";
	}
	
	public void ParsearVenda(String linia) {
		StringTokenizer tokens;
		String seguentParaula;
		Arbre arbre;
		Decoracio decoracio;
		Flor flor;
		
		tokens = new StringTokenizer(linia);
		seguentParaula = tokens.nextToken();
		if(seguentParaula.equals("Arbre")) {
			arbre = new Arbre("",0.0f,0.0f);
			arbre.ParsearArbre(linia);
			this.setVendes(arbre);
		}					
		else if(seguentParaula.equals("Flor")) {				
			flor = new Flor("","",0.0f);
			flor.ParsearFlor(linia);
			this.setVendes(flor);				
		}
		else if(seguentParaula.equals("Decoracio")) {
			decoracio = new Decoracio("",null,0.0f);
			decoracio.ParsearDecoracio(linia);
			this.setVendes(decoracio);
		}		
	};
}
