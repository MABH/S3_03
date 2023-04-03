package entitats;

import connexio.Connexio;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConsultesFloristeria extends Connexio{	
	MongoCollection<Document> mdbCollection;
	
	public ConsultesFloristeria(MongoDatabase mdb) {	
		mdbCollection = mdb.getCollection("floristeries");
	}

	public void insert (String flo) {
		Document floristeria = new Document("_id", new ObjectId());
		floristeria.append("nom", flo);
		mdbCollection.insertOne(new Document(floristeria));			
	}
	
	public boolean read (String flo) {		
		 Document floristeria = mdbCollection.find(new Document("nom", flo)).first();
         if (floristeria!=null) {
        	 return true;
         }
         else return false;
	}
	
	public ObjectId readId (String flo) {		
		 Document floristeria = mdbCollection.find(new Document("nom", flo)).first();
		 if (floristeria!=null) {
			 return floristeria.getObjectId("_id");
		 }
		 else return floristeria.getObjectId("_id");
	}
}
