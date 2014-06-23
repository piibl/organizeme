package com.talsoft.organizeme.core.domain.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("U")
public class SimpleUser extends User {

	public SimpleUser() {

	}

	public SimpleUser(String firstName, String lastName, String login, String password, String email) {
		super(firstName, lastName, login, password, email);
	}

}
