package com.wf.repository.adapter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

public class DocumentAdapter <T extends Object> implements Adapter {
	private DocumentAdapter() {
		super();
	}
	
	private static DocumentAdapter documentAdapter;
	
	public static synchronized DocumentAdapter getInstance() {
		if(documentAdapter == null) {
			documentAdapter = new DocumentAdapter();
		}
		return documentAdapter;
	}
	
	@Override
	public Document getDocument(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		BasicDBObject basicDocument = (BasicDBObject) JSON.parse(json);
		System.out.println(basicDocument);
		/*
		Document document = new Document();
		Field[] fields = object.getClass().getDeclaredFields();
		for(Field field : fields) {
			try {
				if(Modifier.isPrivate(field.getModifiers())) {
					field.setAccessible(true);
					document.append(field.getName(), field.get(object));
					field.setAccessible(false);
				} else if(Modifier.isProtected(field.getModifiers())){
					field.setAccessible(true);
					document.append(field.getName(), field.get(object));
					field.setAccessible(false);
				} else {
					document.append(field.getName(), field.get(object));
				}
			} catch (Exception e) {
				//throws IllegalArgumentException, IllegalAccessException
				System.out.println(e);
			}
			
		}
		*/
		Document document = new Document(basicDocument);
		document.append("_class", object.getClass().getName());
		return document;
	}
	
	@Override
	public T getObject(Document document) {
		Object object = new Object();
		try {
			Class cls = Class.forName(document.getString("_class"));
			object = cls.newInstance();
			
			Gson gson = new Gson();
			String json = gson.toJson(document);
			object = gson.fromJson(json, object.getClass());
			
			/*
			Field[] fields = object.getClass().getDeclaredFields();
			for(Field field : fields) {
				try {
					if(Modifier.isPrivate(field.getModifiers())) {
						field.setAccessible(true);
						if(document.get(field.getName()) != null) {
							field.set(object, document.get(field.getName()));
						}
						field.setAccessible(false);
					} else if(Modifier.isProtected(field.getModifiers())){
						field.setAccessible(true);
						if(document.get(field.getName()) != null) {
							field.set(object, document.get(field.getName()));
						}
						field.setAccessible(false);
					} else {
						if(document.get(field.getName()) != null) {
							field.set(object, document.get(field.getName()));
						}
					}
				} catch (Exception e) {
					//throws IllegalArgumentException, IllegalAccessException
					System.out.println(e);
				}
			}
			*/
		} catch(Exception e) {
			//ClassNotFoundException
			System.out.println(e);
		}
		return (T) object;
	}
	
}
