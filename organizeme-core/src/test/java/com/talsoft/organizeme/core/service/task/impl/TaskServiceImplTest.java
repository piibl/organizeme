/**
 * 
 */
package com.talsoft.organizeme.core.service.task.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.SimpleUser;
import com.talsoft.organizeme.core.domain.user.User;
import com.talsoft.organizeme.core.service.task.TaskService;
import com.talsoft.organizeme.core.service.user.SimpleUserService;

/**
 * @author A547891
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test_spring-config-hsql.xml")
@Transactional
public class TaskServiceImplTest {

	@Inject
	private TaskService taskService;
	@Inject
	private SimpleUserService simpleUserService;

	@Test
	public void testCreateTask() {
		// Persist de l'utilisateur, pour éviter une belle IllegalStateException ; p
		User user = simpleUserService.save(new SimpleUser("Popol", "franck", "123", "123", "popol@123.com"));
		// Assert
		assertNotNull(taskService.save(new Task("test", "high", "Paris", user)));
	}

	@Test
	public void testDeleteTask() {
		// Persist de l'utilisateur, pour éviter une belle IllegalStateException ; p
		User user = simpleUserService.save(new SimpleUser("Popol", "franck", "123", "123", "popol@123.com"));
		// Assert
		Task task = taskService.save(new Task("test", "high", "Paris", user));
		taskService.delete(task);
		assertNull(taskService.find(task.getId()));
	}
}
