package com.talsoft.organizeme.core.service.user.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.user.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

	/**
	 * Retourne l'administrateur correspondant au login passé en paramètre
	 * 
	 * @param login
	 * @return
	 */
	public Administrator findByLogin(String login);

}
