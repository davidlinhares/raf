package repository.facade;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface Database {

	public MongoClient getConnection();
	public MongoDatabase getDatabase();
	
}
