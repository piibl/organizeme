package com.talsoft.organizeme.core.service.user;

import com.talsoft.organizeme.core.domain.user.Administrator;
import com.talsoft.organizeme.core.service.CrudService;

public interface AdministratorService extends CrudService<Administrator, Long> {

	/**
	 * Retourne l'instance correspondante à ce login
	 * 
	 * @param login
	 *            : login de l'utilisateur recherché
	 * @return
	 */
	public Administrator findByLogin(String login);

}
