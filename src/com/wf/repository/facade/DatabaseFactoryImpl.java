package com.wf.repository.facade;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.wf.configuration.ConfigurationReaderStatic;

public class DatabaseFactoryImpl implements DatabaseFactory {
	private static DatabaseFactoryImpl databaseManager = null;
	
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	//private static List<MongoCollection> collections;
	
	private DatabaseFactoryImpl() {
		super();
	}
	
	public static synchronized DatabaseFactoryImpl getInstance() {
		if(databaseManager == null) {
			databaseManager = new DatabaseFactoryImpl();
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
