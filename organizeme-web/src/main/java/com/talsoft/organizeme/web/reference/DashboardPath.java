package com.talsoft.organizeme.web.reference;

public enum DashboardPath {

	USER_DASHBOARD("dashboard"), ADMIN_DASHBOARD("admindashboard");

	private String path;

	private DashboardPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
