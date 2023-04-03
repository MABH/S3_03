package entitats;

import connexio.Connexio;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ConsultesFlors extends Connexio{
	MongoCollection<Document> mdbCollection;
	
	public ConsultesFlors(MongoDatabase mdb) {	
		mdbCollection = mdb.getCollection("flors");
	}

	public void insert (String nom, String color, float preu, Object flori_id) {
		Document flor = new Document("_id", new ObjectId());
		flor.append("nom", nom);
		flor.append("color", color);
		flor.append("preu", preu);
		flor.append("flori_id", flori_id);
		flor.append("ticket_id", "");
		mdbCollection.insertOne(new Document(flor));			
	}
	
	public boolean read (String nom, Object flori_id) {		
		BasicDBObject basicDBObject_SearchCondition = new BasicDBObject();
        basicDBObject_SearchCondition.append("nom", nom).append("flori_id", flori_id);
        Document flor = mdbCollection.find(new Document(basicDBObject_SearchCondition)).first();
        if (flor!=null) {
       	 return true;
        }
        else return false;
	}
	
	public boolean readAll (Object flori_id) {	
		 FindIterable<Document> iterable = mdbCollection.find(new Document("flori_id", flori_id));
		 MongoCursor<Document> cursor = iterable.iterator();
		 if (cursor!=null) {
			System.out.println("Flors:");	
	       	while (cursor.hasNext()) {
	       		Document element = cursor.next();
	       	    System.out.println("Nom: "+element.getString("nom"));
	       	    System.out.println("Color: "+element.getString("color"));
	       	    System.out.println("Preu: "+element.getDouble("preu"));
	       	}
      	 	return true;
		 }
		 else return false;
	}
	
	public void delete (String nom, Object flori_id) {		
		BasicDBObject basicDBObject_SearchCondition = new BasicDBObject();
        basicDBObject_SearchCondition.append("nom", nom).append("flori_id", flori_id);
        DeleteResult result = mdbCollection.deleteOne(new Document(basicDBObject_SearchCondition));
        System.out.println(result);
	}
	
	public void count (Object flori_id) {		
		Document filter = new Document("flori_id", flori_id);
        long result = mdbCollection.countDocuments(filter);
        System.out.println("Nombre de flors: "+result);
	}
	
	public double preuTotal (Object flori_id) {	
		 FindIterable<Document> iterable = mdbCollection.find(new Document("flori_id", flori_id));
		 MongoCursor<Document> cursor = iterable.iterator();
		 double preu = 0;
		 if (cursor!=null) {
		     while (cursor.hasNext()) {
		    	 Document element = cursor.next();       	   
		         preu+=element.getDouble("preu");
	     	}
		 }
		 return preu;
	}
	
	public void update(String nom, Object floriId, Object idTicket) {
		System.out.println("Update flors idTicket: " + idTicket.toString());
        
		Document query = new Document().append("nom",  nom);
		query.append("flori_id", floriId);
        Bson updates = Updates.combine(
                Updates.set("ticket_id", idTicket));
        UpdateOptions options = new UpdateOptions().upsert(true);
        try {
            UpdateResult result = mdbCollection.updateOne(query, updates, options);
            System.out.println("Compres realitzades: " + result.getModifiedCount());
            System.out.println("Identificador de ticket introduit: " + result.getUpsertedId()); // only contains a value when an upsert is performed
        } catch (MongoException me) {
            System.err.println("No s'ha pogut fer la compra degut a un error: " + me);
        }
	}
	
	public void readVendes(Object idTicket){
		FindIterable<Document> iterable = mdbCollection.find(new Document("ticket_id", idTicket));
		 MongoCursor<Document> cursor = iterable.iterator();
		 if (cursor!=null) {
		     while (cursor.hasNext()) {
		    	 Document element = cursor.next();       
		    	 System.out.println("Nom: " + element.getString("nom"));
		    	 System.out.println("Color: " + element.getString("color"));
		    	 System.out.println("Preu: " + element.getDouble("preu"));
			}
		 }else 	System.out.println("No s'han comprat flors.");   	 
	}
	
	public double readFacturacio(Object idTicket) {
		double preu=0;
		FindIterable<Document> iterable = mdbCollection.find(new Document("ticket_id", idTicket));
		MongoCursor<Document> cursor = iterable.iterator();
		if (cursor!=null) {
		   while (cursor.hasNext()) {
		    	 Document element = cursor.next();    
		    	 preu += element.getDouble("preu");
		   }
		}else 	System.out.println("No s'han comprat flors.");
		return preu;   	 
	}
}
