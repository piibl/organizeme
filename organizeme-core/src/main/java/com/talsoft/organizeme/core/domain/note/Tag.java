package com.talsoft.organizeme.core.domain.note;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.talsoft.organizeme.core.util.converter.time.DateTimeConverter;

@Entity
@Table(name = "TAG")
public class Tag {

	/*
	 * ID
	 */
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tagSeq")
	@SequenceGenerator(name = "TagSeq", sequenceName = "TAG_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ID")
	@Id
	private Long id;

	/**
	 * Intitulé du tag
	 */
	@Column(name = "LABEL")
	private String label;

	/**
	 * couleur du tag
	 */
	@Column(name = "COLOR")
	private String color;

	@Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime createdAt;

	public Tag() {

	}

	/**
	 * Constructeur
	 * 
	 * @param label
	 *            : intitulé du tag
	 * @param color
	 *            : couleur du tag
	 */
	public Tag(String label, String color) {
		this.label = label;
		this.color = color;
		this.createdAt = new DateTime();
	}

	/*****************
	 * MUTATEURS
	 *****************/

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public String getColor() {
		return color;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

}
