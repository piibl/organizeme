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

import com.talsoft.organizeme.core.domain.user.User;
import com.talsoft.organizeme.core.util.converter.time.DateTimeConverter;

/**
 * Tâche
 * 
 * @author A547891
 */
@Entity
@Table(name = "TASK")
public class Task {

	/**
	 * ID
	 */
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TaskSeq")
	@SequenceGenerator(name = "TaskSeq", sequenceName = "TASK_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ID")
	@Id
	private Long id;
	// TODO à monter en objet, multiples buts possibles ?
	/**
	 * But de la tâche
	 */
	@Column(name = "GOAL")
	private String goal;
	// TODO enum
	/**
	 * Priorité de la tâche
	 */
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
	 * Propriétaire de la tâche
	 */
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User owner;

	/**
	 * Date de crétaion de la note
	 */
	@Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime createdAt;
	/**
	 * Archivé
	 */
	@Column(name = "ARCHIVED")
	private boolean archived;

	public Task() {

	}

	/**
	 * @param goal
	 *            : but de la tâche
	 * @param priority
	 *            : priorité de la tâche
	 * @param localization
	 *            : localisation de la tâche
	 * @param periodDay
	 *            : nombre de jours pour réaliser la tâche
	 * @param periodMonth
	 *            : nombre de mois pour réaliser la tâche
	 */
	public Task(String goal, String priority, String localization, User owner) {
		this.goal = goal;
		this.priority = priority;
		this.localization = localization;
		this.createdAt = new DateTime();
		this.archived = false;
		this.owner = owner;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
