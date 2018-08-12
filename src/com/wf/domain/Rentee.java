package com.wf.domain;

import com.wf.domain.annotation.Attendant;

@Attendant
public abstract class Rentee {
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract Rentee clone();
}
