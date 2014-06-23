package com.talsoft.organizeme.core.service.user;

import com.talsoft.organizeme.core.domain.user.SimpleUser;
import com.talsoft.organizeme.core.service.CrudService;

public interface SimpleUserService extends CrudService<SimpleUser, Long> {

	/**
	 * Retourne l'instance correspondante à ce login
	 * 
	 * @param login
	 *            : login de l'utilisateur recherché
	 * @return
	 */
	public SimpleUser findByLogin(String login);

}
