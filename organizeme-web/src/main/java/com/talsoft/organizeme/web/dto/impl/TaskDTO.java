package com.talsoft.organizeme.web.dto.impl;

import java.io.Serializable;

import com.talsoft.organizeme.web.dto.IDTO;

/**
 * Objet tampon servant à la conversion Json -> Objet
 */
public class TaskDTO implements Serializable, IDTO {

	private String title;
	private String goal;
	private String priority;
	private String localization;
	private String startDate;
	private String endDate;

	private Long ownerId;

	public String getTitle() {
		return title;
	}

	public String getGoal() {
		return goal;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getPriority() {
		return priority;
	}

	public String getLocalization() {
		return localization;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

}
