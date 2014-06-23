package com.talsoft.organizeme.core.service.repository.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.User;
import com.talsoft.organizeme.core.domain.task.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	public List<Task> findByOwner(User owner);

}
