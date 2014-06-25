package com.talsoft.organizeme.core.service.user.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.user.EndUser;

public interface UserRepository extends JpaRepository<EndUser, Long> {

	/**
	 * Retourne l'utilisateur correspondant au login passé en paramètre
	 * 
	 * @param login
	 * @return
	 */
	public EndUser findByLogin(String login);

}
