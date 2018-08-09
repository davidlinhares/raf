package repository.facade;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.model.Filters;

import repository.adapter.DocumentAdapter;

public interface Repository <T extends Object> {

	public default Class getGenericType() {
		return ((Class<T>) getClass());
		//return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getTypeName();
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
		DatabaseManager.getInstance().getCollection("Person").find().forEach(convertBlock);
		return objects;
	}

	public default T find(String field, String id) {
		System.out.println(getGenericType().getSimpleName());
		Document document = DatabaseManager.getInstance().getCollection("Person").find(Filters.eq(field, id)).first();
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
	}
	
	public default void update(T oldObject, T newObject) {
		
	}
	
	public default void save(T object) {
		DatabaseManager.getInstance().getCollection(object.getClass().getSimpleName()).insertOne(DocumentAdapter.getInstance().getDocument(object));
	}
	
}
