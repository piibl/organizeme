package com.talsoft.organizeme.core.service.task.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.service.AbstractCrudService;
import com.talsoft.organizeme.core.service.task.TaskService;
import com.talsoft.organizeme.core.service.task.impl.repository.TaskRepository;

@Named
public class TaskServiceImpl extends AbstractCrudService<Task, Long> implements TaskService {

	@Inject
	private TaskRepository taskRepository;

	@Override
	protected JpaRepository<Task, Long> getRepository() {
		return taskRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Task> findByOwner(EndUser owner) {
		return taskRepository.findByOwnerAndStartDateGreaterThanOrderByStartDateAsc(owner, new DateTime());
	}

}
