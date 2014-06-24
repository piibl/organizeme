package com.talsoft.organizeme.web.reference.path;

public enum GlobalPath {

	HOME("home"), DENIED("denied"), ;

	private String path;

	private GlobalPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
