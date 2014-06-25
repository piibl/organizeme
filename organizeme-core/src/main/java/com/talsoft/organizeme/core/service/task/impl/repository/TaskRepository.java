package com.talsoft.organizeme.core.service.task.impl.repository;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.EndUser;

public interface TaskRepository extends JpaRepository<Task, Long> {

	public List<Task> findByOwner(EndUser owner);

	public List<Task> findByOwnerAndStartDateGreaterThanOrderByStartDateAsc(EndUser owner, DateTime date);

	public List<Task> findByOwnerAndEndDateGreaterThanOrderByStartDateAsc(EndUser owner, DateTime date);

	public List<Task> findByOwnerAndEndDateLessThanOrderByStartDateAsc(EndUser owner, DateTime date);

}
