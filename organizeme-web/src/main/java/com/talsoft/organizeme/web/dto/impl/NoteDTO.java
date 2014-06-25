package com.talsoft.organizeme.web.dto.impl;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.talsoft.organizeme.web.dto.IDTO;

/**
 * Objet tampon servant à la conversion Json -> Objet
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDTO implements Serializable, IDTO {

	private String title;
	private String content;

	// private String tag;

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	// public String getTag() {
	// return tag;
	// }

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// public void setTag(String tag) {
	// this.tag = tag;
	// }

}
