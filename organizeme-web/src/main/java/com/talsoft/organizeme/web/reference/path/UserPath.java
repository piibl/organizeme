package com.talsoft.organizeme.web.reference.path;

public enum UserPath {

	NOTIFICATIONS("#"), NEWS("#"), TASKS("tasks"), NOTES("notes");

	private String path;

	private UserPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
