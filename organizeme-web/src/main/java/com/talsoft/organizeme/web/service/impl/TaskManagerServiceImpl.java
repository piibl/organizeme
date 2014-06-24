package com.talsoft.organizeme.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.User;
import com.talsoft.organizeme.core.service.AbstractCrudService;
import com.talsoft.organizeme.core.service.task.impl.repository.TaskRepository;
import com.talsoft.organizeme.web.service.TaskManagerService;

@Named
public class TaskManagerServiceImpl extends AbstractCrudService<Task, Long> implements TaskManagerService {

	@Inject
	private TaskRepository taskRepository;

	@Override
	public List<Task> findByOwner(User owner) {
		return taskRepository.findByOwner(owner);
	}

	@Override
	protected JpaRepository<Task, Long> getRepository() {
		return taskRepository;
	}

}
