package com.talsoft.organizeme.web.service;

import java.util.List;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.web.dto.impl.TaskDTO;

public interface TaskManagerService extends OwnedDomainObjectService<Task, Long, EndUser, TaskDTO> {

	List<Task> findByOwnerNonArchived(EndUser owner);

}
