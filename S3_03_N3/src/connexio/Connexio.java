package connexio;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class Connexio {	
	MongoDatabase mdb;
	MongoClient client;
	
	
	public Connexio() {		
		MongoClient client = MongoClients.create("mongodb+srv://mabh:Ridici2014@cluster0.cnssqaj.mongodb.net/test");
		mdb = client.getDatabase("floristeria");
		System.out.println("db: "+mdb.getName());
		//MongoCollection<Document> floristeriaCollection = mdb.getCollection("floristeries");

		//Document nom = floristeriaCollection.find().first();
		//System.out.println("nom: "+nom.getString("nom"));
	}
	
	public MongoDatabase getDb() {
		return mdb;		
	}
}
