package com.talsoft.organizeme.web.reference.link;

/**
 * Rescense le nom des liens dans les templates thymeleaf
 */
public enum LinkName {

	// lien redirection
	REDIRECT("redirectLink"),
	// lien news
	NEWS("newslink"),
	// lien dashboard
	DASHBOARD("dashboardLink"),
	// lien notifications
	NOTIFICATIONS("notificationsLink"),
	// lien tâches
	TASKS("tasksLink"),
	// lien nouvellestâches
	NEW_TASK("newTaskLink"),
	// lien notes
	NOTES("notesLink"),
	// lien nouvellestâches
	NEW_NOTE("newNoteLink"),
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
