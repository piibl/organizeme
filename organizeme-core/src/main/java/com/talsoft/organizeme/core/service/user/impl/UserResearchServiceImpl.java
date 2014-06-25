package com.talsoft.organizeme.core.service.user.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.service.user.UserResearchService;
import com.talsoft.organizeme.core.service.user.impl.repository.UserRepository;

@Named
public class UserResearchServiceImpl implements UserResearchService {

	@Inject
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public EndUser findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

}
