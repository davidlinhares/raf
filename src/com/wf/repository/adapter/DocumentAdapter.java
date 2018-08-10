package com.wf.repository.adapter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.bson.Document;

public class DocumentAdapter <T extends Object> {
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
	
	public Document getDocument(T object) {
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
		document.append("_class", object.getClass().getName());
		return document;
	}
	
	public T getObject(Document document) {
		Object object = new Object();
		try {
			Class cls = Class.forName(document.getString("_class"));
			object = cls.newInstance();
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
		} catch(Exception e) {
			//ClassNotFoundException
			System.out.println(e);
		}
		return (T) object;
	}
}
