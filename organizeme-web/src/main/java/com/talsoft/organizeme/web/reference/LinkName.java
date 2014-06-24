package com.talsoft.organizeme.web.reference;

/**
 * Rescense le nom des liens dans les templates thymeleaf
 */
public enum LinkName {
	// lien news
	NEWS("newslink"),
	// lien dashboard
	DASHBOARD("dashboardLink"),
	// lien notifications
	NOTIFICATIONS("notificationsLink"),
	// lien tâches
	TASKS("taskslink"),
	// lien notes
	NOTES("notesLink"),
	// Lien agenda
	PLANNER("plannerLink");

	private String name;

	private LinkName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
