package com.talsoft.organizeme.core.domain.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Administrator extends EndUser {

	public Administrator() {

	}

	public Administrator(String firstName, String lastName, String login, String password, String email) {
		super(firstName, lastName, login, password, email);
	}

}
