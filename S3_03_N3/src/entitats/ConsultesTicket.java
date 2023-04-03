package entitats;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import connexio.Connexio;

public class ConsultesTicket extends Connexio{
	MongoCollection<Document> mdbCollection;
	
	public ConsultesTicket(MongoDatabase mdb) {	
		mdbCollection = mdb.getCollection("ticket");
	}
	
	public void insert (String codi, Object flori_id) {
		Document ticket = new Document("_id", new ObjectId());	
		ticket.append("codi", codi);
		ticket.append("flori_id", flori_id);
		mdbCollection.insertOne(new Document(ticket));			
	}
	
	public Object readId (Object flori_id, String codi) {	
		Object id=null;
		Document ticket = new Document("codi", codi);	
		ticket.append("flori_id", flori_id);
		FindIterable<Document> iterable = mdbCollection.find(new Document(ticket));
		 MongoCursor<Document> cursor = iterable.iterator();
		 if (cursor!=null) {
	       	while (cursor.hasNext()) {
	       		Document element = cursor.next();
	       	    id=element.get("_id");
	       	}
     	 	return id;
		 }
		 else return id;	
	}
	
	public ArrayList<Ticket> readAll (Object flori_id) {	
		 ArrayList<Ticket> idTicketList = new ArrayList<Ticket>();
		 Ticket ticket = new Ticket();
		 Document ticketD = new Document("flori_id", flori_id);
		 FindIterable<Document> iterable = mdbCollection.find(new Document(ticketD));
		 MongoCursor<Document> cursor = iterable.iterator();
		 if (cursor!=null) {
	       	while (cursor.hasNext()) {
	       		ticket = new Ticket();
	       		Document element = cursor.next();
	       		ticket.setId(element.get("_id"));
	       		ticket.setCodi(element.getString("codi"));
	       		ticket.setIdFlori(element.get("flori_id"));
	       		idTicketList.add(ticket);	       		
	       	}
		 }else return idTicketList;
		 		 
		 return idTicketList;
	}
}
