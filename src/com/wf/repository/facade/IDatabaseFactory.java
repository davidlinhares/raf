package com.wf.repository.facade;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public interface IDatabaseFactory {

	public MongoClient getConnection();
	public MongoDatabase getDatabase();
	public MongoCollection<Document> getCollection(String name);
}
