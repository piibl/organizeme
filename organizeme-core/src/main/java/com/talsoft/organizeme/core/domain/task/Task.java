package com.talsoft.organizeme.core.domain.task;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.talsoft.organizeme.core.domain.user.EndUser;
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

	/**
	 * titre de la tâche
	 */
	@Column(name = "TITLE")
	private String title;

	/**
	 * But de la tâche
	 */
	@Lob
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
	private EndUser owner;

	/**
	 * Date de crétaion de la tache
	 */
	@Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime createdAt;

	@Column(name = "START_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime startDate;

	@Column(name = "END_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime endDate;

	/**
	 * Archivé
	 */
	@Column(name = "ARCHIVED")
	private boolean archived;

	public Task() {

	}

	/**
	 * @param title
	 *            : titre de la tâche
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
	public Task(String title, String goal, String priority, String localization, EndUser owner, DateTime startDate, DateTime endDate) {
		this.title = title;
		this.goal = goal;
		this.priority = priority;
		this.localization = localization;
		this.createdAt = new DateTime();
		this.archived = false;
		this.owner = owner;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public EndUser getOwner() {
		return owner;
	}

	public void setOwner(EndUser owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
}
