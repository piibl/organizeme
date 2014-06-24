package com.talsoft.organizeme.web.service;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.User;

public interface TaskManagerService extends OwnedDomainObjectService<Task, Long, User> {

}
