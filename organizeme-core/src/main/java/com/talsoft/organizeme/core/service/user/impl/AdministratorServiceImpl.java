package com.talsoft.organizeme.core.service.user.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.talsoft.organizeme.core.domain.user.Administrator;
import com.talsoft.organizeme.core.service.AbstractCrudService;
import com.talsoft.organizeme.core.service.user.AdministratorService;
import com.talsoft.organizeme.core.service.user.impl.repository.AdministratorRepository;

@Named
public class AdministratorServiceImpl extends AbstractCrudService<Administrator, Long> implements AdministratorService {

	@Inject
	private AdministratorRepository administratorRepository;

	@Override
	@Transactional(readOnly = true)
	public Administrator findByLogin(String login) {
		return administratorRepository.findByLogin(login);
	}

	@Override
	protected JpaRepository<Administrator, Long> getRepository() {
		return administratorRepository;
	}

}
