package com.talsoft.organizeme.core.service.task.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.service.AbstractCrudService;
import com.talsoft.organizeme.core.service.task.TaskService;
import com.talsoft.organizeme.core.service.task.impl.repository.TaskRepository;

@Named
public class TaskServiceImpl extends AbstractCrudService<Task, Long> implements TaskService {

	@Inject
	private TaskRepository taskRepository;

	@Override
	protected JpaRepository<Task, Long> getDAO() {
		return taskRepository;
	}

}
