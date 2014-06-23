package com.talsoft.organizeme.core.service.task.impl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

	public List<Task> findByOwner(User owner);

}
