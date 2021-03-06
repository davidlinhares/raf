package com.wf.repository.facade;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.wf.configuration.ConfigurationReaderStatic;

public class DatabaseFactory implements IDatabaseFactory {
	private static DatabaseFactory databaseManager = null;
	
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	private Level logginglevel = Level.INFO;
	//private static List<MongoCollection> collections;
	
	private DatabaseFactory() {
		super();
	}
	
	public static synchronized DatabaseFactory getInstance() {
		if(databaseManager == null) {
			databaseManager = new DatabaseFactory();
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
		
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(getLoggingLevel());
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

	@Override
	public Level getLoggingLevel() {
		try {
			if(ConfigurationReaderStatic.getDatabaseConfiguration().get("logging") != null && 
					Level.parse(ConfigurationReaderStatic.getDatabaseConfiguration().get("logging")) != null) {
				logginglevel = Level.parse(ConfigurationReaderStatic.getDatabaseConfiguration().get("logging"));
			}
		} catch (Exception e) {
			//IOException
			System.out.println(e);
		}
		return logginglevel;
	}
	
}
