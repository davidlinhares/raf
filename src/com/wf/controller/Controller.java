package com.wf.controller;

import com.wf.repository.facade.Repository;

public abstract class Controller {

	@SuppressWarnings("rawtypes")
	protected Repository repository;

	@SuppressWarnings("unchecked")
	public <T> void add(T object) {
		this.repository.save(object);
	}

	@SuppressWarnings("unchecked")
	public <T> T find(String name) {
		return (T) this.repository.find("name", name);
	}

}
