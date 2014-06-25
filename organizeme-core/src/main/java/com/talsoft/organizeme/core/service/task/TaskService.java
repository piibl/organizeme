package com.talsoft.organizeme.core.service.task;

import java.util.List;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.service.CrudService;

public interface TaskService extends CrudService<Task, Long> {

	/**
	 * Retourne toutes les tâches d'un utilisateur
	 */
	public List<Task> findByOwner(EndUser owner);
}
