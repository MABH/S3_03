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

import static com.mongodb.client.model.Filters.eq;

public class ConsultesArbres extends Connexio{
	MongoCollection<Document> mdbCollection;
	
	public ConsultesArbres(MongoDatabase mdb) {	
		mdbCollection = mdb.getCollection("arbres");
	}

	public void insert (String nom, float alçada, float preu, Object flori_id) {
		Document arbre = new Document("_id", new ObjectId());
		arbre.append("nom", nom);
		arbre.append("alçada", alçada);
		arbre.append("preu", preu);
		arbre.append("flori_id", flori_id);
		arbre.append("ticket_id", "");
		mdbCollection.insertOne(new Document(arbre));			
	}
	
	public boolean read (String nom, Object flori_id) {		
		 BasicDBObject basicDBObject_SearchCondition = new BasicDBObject();
         basicDBObject_SearchCondition.append("nom", nom).append("flori_id", flori_id);
         Document arbre = mdbCollection.find(new Document(basicDBObject_SearchCondition)).first();
         if (arbre!=null) {
        	 return true;
         }
         else return false;
	}
	
	public boolean readAll (Object flori_id) {	
		 FindIterable<Document> iterable = mdbCollection.find(new Document("flori_id", flori_id));
		 MongoCursor<Document> cursor = iterable.iterator();
		 if (cursor!=null) {
			System.out.println("Arbre:");	
        	while (cursor.hasNext()) {
        		Document element = cursor.next();
        	    System.out.println("Nom: "+element.getString("nom"));
        	    System.out.println("Alçada: "+element.getDouble("alçada"));
        	    System.out.println("Preu: "+element.getDouble("preu"));
        	}
       	 	return true;
		 }
		 else return false;
	}
	
	public void delete (String nom, Object flori_id) {		
		Document filter = new Document("nom", nom);
		filter.append("flori_id", flori_id);
        DeleteResult result = mdbCollection.deleteOne(filter);
        System.out.println(result);
	}
	
	public void count (Object flori_id) {		
		Document filter = new Document("flori_id", flori_id);
        long result = mdbCollection.countDocuments(filter);
        System.out.println("Nombre de arbres: "+result);
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
		Document query = new Document().append("nom",  nom);
		query.append("flori_id", floriId);
        Bson updates = Updates.combine(
                Updates.set("ticket_id", idTicket));
        UpdateOptions options = new UpdateOptions().upsert(true);
        try {
            UpdateResult result = mdbCollection.updateOne(query, updates, options);
            System.out.println("Compres realitzades: " + result.getModifiedCount());
            } catch (MongoException me) {
            System.err.println("No s'ha pogut fer la compra degut a un error: " + me);
        }
	}
	
	public void readVendes(Object idTicket){
		FindIterable<Document> iterable = mdbCollection.find(new Document("ticked_id", idTicket));
		 MongoCursor<Document> cursor = iterable.iterator();
		 if (cursor!=null) {
		     while (cursor.hasNext()) {
		    	 Document element = cursor.next();       
		    	 System.out.println("Nom: " + element.getString("nom"));
		    	 System.out.println("Alçada: " + element.getDouble("alçada"));
		    	 System.out.println("Preu: " + element.getDouble("preu"));
			}
		 }else 	System.out.println("No s'han comprat arbres.");   	 
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
		}else 	System.out.println("No s'han comprat arbres.");
		return preu;   	 
	}
}
