package com.talsoft.organizeme.core.service.user.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.user.SimpleUser;

public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {

	/**
	 * Retourne l'utilisateur correspondant au login passé en paramètre
	 * 
	 * @param login
	 * @return
	 */
	public SimpleUser findByLogin(String login);

}
