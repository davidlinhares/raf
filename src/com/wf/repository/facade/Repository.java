package com.wf.repository.facade;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.common.reflect.TypeToken;
import com.mongodb.Block;
import com.mongodb.client.model.Filters;
import com.wf.repository.adapter.DocumentAdapter;

public interface Repository <T extends Object> {

	public default Type getGenericType() {
		try {
			TypeToken<T> typeToken = new TypeToken<T>(getClass()) { };
			Type type = typeToken.getType();
			return type;
		} catch(Exception e) {
			System.out.println("Repository - Line 25");
			System.out.println("Could not get the type for this generic type.");
			System.out.println(e);
		}
		return null;
	}
	
	public default Class<T> getGenericClass() {
		try {
			if(getGenericType() != null) {
				Class klass = Class.forName(getGenericType().getTypeName().toString());
				return klass;
			} else {
				System.out.println("Error while getting the type for the generic type.");
				return null;
			}
		} catch(Exception e) {
			System.out.println("Repository - Line 41");
			System.out.println(e);
		}
		return null;
	}
	
	public default Object getObjectOfT() {
		try {
			if(getGenericClass() != null) {
				Object object = getGenericClass().newInstance();
				return object;
			} else {
				System.out.println("Error while getting the class for the generic type.");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Repository - Line 55");
			System.out.println(e);
		}
		return null;
	}
	
	public default List<T> findAll(){
		List<T> objects = new ArrayList<T>();
		
		Block<Document> convertBlock = new Block<Document>() {
		       @Override
		       public void apply(final Document document) {
		    	   try {
		    		   DocumentAdapter<T> documentAdapter = DocumentAdapter.getInstance();
			    	   T object = documentAdapter.getObject(document);
			    	   objects.add(object);
		    	   } catch(Exception e) {
		    		   System.out.println(e);
		    	   }
		    	   
		       }
		};
		DatabaseFactory.getInstance().getCollection(getObjectOfT().getClass().getSimpleName()).find().forEach(convertBlock);
		return objects;
	}

	public default T find(String field, String id) {
		try {
			if(getObjectOfT() != null) {
				Document document = DatabaseFactory.getInstance().getCollection(getObjectOfT().getClass().getSimpleName()).find(Filters.eq(field, id)).first();
				if(document != null) {
					try {
						DocumentAdapter<T> documentAdapter = DocumentAdapter.getInstance();
						return documentAdapter.getObject(document);
					} catch(Exception e) {
						System.out.println(e);
						return null;
					}
				} else {
					System.out.println("Document not found.");
					return null;
				}
			} else {
				System.out.println("Repository - Line 99");
				return null;
			}
		} catch(Exception e) {
			System.out.println("Class not found exception!");
			System.out.println(e);
		}
		return null;
	}
	
	public default void update(String field, String id, T newObject) {
		T object = find(field, id);
		if(object != null) {
			Document document = DatabaseFactory.getInstance().getCollection(getObjectOfT().getClass().getSimpleName()).find(Filters.eq(field, id)).first();
			DatabaseFactory.getInstance().getCollection(getObjectOfT().getClass().getSimpleName()).replaceOne(Filters.eq("_id", new ObjectId(document.get("_id").toString())), DocumentAdapter.getInstance().getDocument(newObject));
		} else {
			save(newObject);
		}
		
	}
	
	public default void save(T object) {
		//Alternative way of getting the object type for matching the database.
		//DatabaseManager.getInstance().getCollection(object.getClass().getSimpleName()).insertOne(DocumentAdapter.getInstance().getDocument(object));
		DatabaseFactory.getInstance().getCollection(getObjectOfT().getClass().getSimpleName()).insertOne(DocumentAdapter.getInstance().getDocument(object));
	}
	
}
