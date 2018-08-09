package repository.facade;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import configuration.ConfigurationReaderStatic;

public class DatabaseManager implements Database {
	private static DatabaseManager databaseManager = null;
	
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	//private static List<MongoCollection> collections;
	
	private DatabaseManager() {
		super();
	}
	
	public static synchronized DatabaseManager getInstance() {
		if(databaseManager == null) {
			databaseManager = new DatabaseManager();
		}
		return databaseManager;
	}
	
	@Override
	public MongoClient getConnection() {
		try {
			if(ConfigurationReaderStatic.getDatabaseConfiguration().get("host") != null) {
				if(ConfigurationReaderStatic.getDatabaseConfiguration().get("port") != null) {
					mongoClient = new MongoClient(ConfigurationReaderStatic.getDatabaseConfiguration().get("host"), Integer.parseInt(ConfigurationReaderStatic.getDatabaseConfiguration().get("port")));
				} else {
					mongoClient = new MongoClient(ConfigurationReaderStatic.getDatabaseConfiguration().get("host"));
				}
			} else {
				mongoClient = new MongoClient();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		
		//Logger mongoLogger = Loggers.getLogger( "org.mongodb.driver" );
		//mongoLogger.setLevel(Level.SEVERE)
		return mongoClient;
	}
	
	@Override
	public MongoDatabase getDatabase() {
		try {
			if(ConfigurationReaderStatic.getDatabaseConfiguration().get("database") != null) {
				database = getConnection().getDatabase(ConfigurationReaderStatic.getDatabaseConfiguration().get("database"));
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return database;
	}
	
	public boolean databaseAuthentication() {
		try {
			if(ConfigurationReaderStatic.getDatabaseConfiguration().get("user") != null &&
					ConfigurationReaderStatic.getDatabaseConfiguration().get("password") != null) {
				
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return false;
	}
	
	public MongoCollection<Document> getCollection(String name) {
		collection = getDatabase().getCollection(name);
		return collection;
	}
	
}
