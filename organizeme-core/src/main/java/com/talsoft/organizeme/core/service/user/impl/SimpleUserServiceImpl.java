package com.talsoft.organizeme.core.service.user.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.talsoft.organizeme.core.domain.user.SimpleUser;
import com.talsoft.organizeme.core.service.AbstractCrudService;
import com.talsoft.organizeme.core.service.user.SimpleUserService;
import com.talsoft.organizeme.core.service.user.impl.repository.SimpleUserRepository;

@Named
public class SimpleUserServiceImpl extends AbstractCrudService<SimpleUser, Long> implements SimpleUserService {

	@Inject
	private SimpleUserRepository simpleUserRepository;

	@Override
	@Transactional(readOnly = true)
	public SimpleUser findByLogin(String login) {
		return simpleUserRepository.findByLogin(login);
	}

	@Override
	protected JpaRepository<SimpleUser, Long> getDAO() {
		return simpleUserRepository;
	}

}
