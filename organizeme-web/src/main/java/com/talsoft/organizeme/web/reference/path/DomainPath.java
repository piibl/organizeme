package com.talsoft.organizeme.web.reference.path;

public enum DomainPath {
	EDIT("edit"), DELETE("delete"), NEW("new"), PARTIAL("partial");
	private String path;

	private DomainPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
