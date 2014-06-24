package com.talsoft.organizeme.web.reference.relation;

public enum GlobalRelation {

	SELF("self"), EDIT("edit"), DELETE("delete");

	private String name;

	private GlobalRelation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
