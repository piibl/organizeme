package com.talsoft.organizeme.core.service.user.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Retourne l'utilisateur correspondant au login passé en paramètre
	 * 
	 * @param login
	 * @return
	 */
	public User findByLogin(String login);

}
