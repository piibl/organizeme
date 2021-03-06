package com.talsoft.organizeme.core.domain.task;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.talsoft.organizeme.core.util.converter.time.DateTimeConverter;

/**
 * Entité représentant une sous-tâche TODO héritage ?
 * 
 * @author A547891
 */
@Entity
@Table(name = "SUB_TASK")
public class SubTask {

	/**
	 * ID
	 */
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SubTaskSeq")
	@SequenceGenerator(name = "SubTaskSeq", sequenceName = "SUB_TASK_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ID")
	@Id
	private Long id;
	/**
	 * But de la sous-tâche
	 */
	// TODO à monter en objet, multiples buts possibles ?
	@Column(name = "GOAL")
	private String goal;
	/**
	 * Degré de priorité de la tâche
	 */
	// TODO enum
	@Column(name = "PRIORITY")
	private String priority;
	/**
	 * Localisation de la tâche
	 */
	// TODO objet
	@Column(name = "LOCALIZATION")
	private String localization;
	// TODO objet
	// private int periodDay;
	// private int periodMonth;
	/**
	 * Date de création
	 */
	@Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime createdAt;
	/**
	 * Archivé
	 */
	@Column(name = "ARCHIVED")
	private boolean archived;

	@ManyToOne
	@JoinColumn(name = "TASK_ID")
	private Task rootTask;

	public SubTask() {

	}

	/**
	 * @param goal
	 * @param priority
	 * @param localization
	 * @param periodDay
	 * @param periodMonth
	 * @param createdAt
	 * @param archived
	 * @param rootTask
	 */
	public SubTask(String goal, String priority, String localization, Task rootTask) {
		this.goal = goal;
		this.priority = priority;
		this.localization = localization;

		this.createdAt = new DateTime();
		this.archived = false;
		this.rootTask = rootTask;
	}

	public Long getId() {
		return id;
	}

	public String getGoal() {
		return goal;
	}

	public String getPriority() {
		return priority;
	}

	public String getLocalization() {
		return localization;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public boolean isArchived() {
		return archived;
	}

	public Task getRootTask() {
		return rootTask;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public void setRootTask(Task rootTask) {
		this.rootTask = rootTask;
	}
}
