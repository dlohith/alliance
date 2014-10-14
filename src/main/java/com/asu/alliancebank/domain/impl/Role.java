package com.asu.alliancebank.domain.impl;

import com.asu.alliancebank.domain.IRole;
/**
 * 
 * @author Lohith
 *
 */
public class Role implements IRole {

	private String id;
	private String name;
	private String description;

	@Override
	public String getId() {
		return id;
	}


	@Override
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String getName() {
		return name;
	}


	@Override
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String getDescription() {
		return description;
	}


	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
