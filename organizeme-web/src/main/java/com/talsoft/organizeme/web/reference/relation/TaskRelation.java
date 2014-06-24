package com.talsoft.organizeme.web.reference.relation;

public enum TaskRelation {
	NOTES("notes");
	private String name;

	private TaskRelation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
