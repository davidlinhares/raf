package com.wf.repository.adapter;

import org.bson.Document;

public interface Adapter <T extends Object> {
	public Document getDocument(T object);
	public T getObject(Document document);
}
