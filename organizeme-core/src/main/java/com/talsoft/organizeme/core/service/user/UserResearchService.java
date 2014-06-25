package com.talsoft.organizeme.core.service.user;

import com.talsoft.organizeme.core.domain.user.EndUser;

public interface UserResearchService {

	/**
	 * Retourne l'instance correspondante à ce login
	 * 
	 * @param login
	 *            : login de l'utilisateur recherché
	 * @return
	 */
	public EndUser findByLogin(String login);

}
