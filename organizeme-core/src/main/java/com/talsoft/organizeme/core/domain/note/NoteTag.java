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

import com.talsoft.organizeme.core.util.converter.time.DateTimeConverter;

/**
 * Entité représentant une association Note-tag
 */
@Entity
@Table(name = "NOTE_TAG")
public class NoteTag {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NoteTagSeq")
	@SequenceGenerator(name = "NoteTagSeq", sequenceName = "NOTE_TAG_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ID")
	@Id
	private Long id;

	/**
	 * Note associée
	 */
	@JoinColumn(name = "NOTE_ID")
	@ManyToOne
	private Note note;
	/**
	 * Tag associé
	 */
	@JoinColumn(name = "TAG_ID")
	@ManyToOne
	private Tag tag;

	/**
	 * Date de création
	 */
	@Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime createdAt;

	public NoteTag() {

	}

	/**
	 * @param note
	 * @param tag
	 */
	public NoteTag(Note note, Tag tag) {
		this.note = note;
		this.tag = tag;
		this.createdAt = new DateTime();
	}

	public Long getId() {
		return id;
	}

	public Note getNote() {
		return note;
	}

	public Tag getTag() {
		return tag;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

}
