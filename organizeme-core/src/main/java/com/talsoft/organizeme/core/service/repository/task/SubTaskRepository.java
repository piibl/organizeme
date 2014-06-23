package com.talsoft.organizeme.core.service.repository.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.task.SubTask;
import com.talsoft.organizeme.core.domain.task.Task;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

	public List<SubTask> findByRootTask(Task task);

}
