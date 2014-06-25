package com.talsoft.organizeme.core.domain.note;

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

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.util.converter.time.DateTimeConverter;

/**
 * Note
 * 
 * @author A547891
 */
@Entity
@Table(name = "NOTE")
public class Note {

	/**
	 * ID
	 */
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NoteSeq")
	@SequenceGenerator(name = "NoteSeq", sequenceName = "NOTE_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ID")
	@Id
	private Long id;
	/**
	 * Titre de la note
	 */
	@Column(name = "TITLE")
	private String title;
	/**
	 * Date de création de la note
	 */
	@Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime createdAt;

	@Column(name = "ARCHIVED")
	private boolean archived;

	/**
	 * Tache de référence, peut être nul
	 */
	@JoinColumn(name = "TASK_ID")
	@ManyToOne
	private Task masterTask;
	/**
	 * Propriétaire de la note
	 */
	@JoinColumn(name = "END_USER_ID")
	@ManyToOne
	private EndUser owner;

	public Note() {

	}

	/**
	 * @param title
	 * @param tags
	 * @param createdAt
	 * @param archived
	 * @param masterTask
	 * @param owner
	 */
	public Note(String title, DateTime createdAt, boolean archived, Task masterTask, EndUser owner) {
		this.title = title;
		this.createdAt = new DateTime();
		this.archived = false;
		this.masterTask = masterTask;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
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

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

}
