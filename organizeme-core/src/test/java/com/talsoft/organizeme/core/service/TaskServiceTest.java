/**
 * 
 */
package com.talsoft.organizeme.core.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.talsoft.organizeme.core.domain.User;
import com.talsoft.organizeme.core.domain.task.Task;

/**
 * @author A547891
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test_spring-config-hsql.xml")
@Transactional
public class TaskServiceTest {

	@Inject
	private TaskService taskService;
	@Inject
	private UserService userService;

	@Test
	public void testCreateTask() {
		// Persist de l'utilisateur, pour éviter une belle IllegalStateException ; p
		User user = userService.createUser("Popol", "franck", "123", "123", "popol@123.com");
		// Assert
		assertNotNull(taskService.createTask("test", "high", "Paris", user));
	}

	@Test
	public void testDeleteTask() {
		// Persist de l'utilisateur, pour éviter une belle IllegalStateException ; p
		User user = userService.createUser("Popol", "franck", "123", "123", "popol@123.com");
		// Assert
		Task task = taskService.createTask("test", "high", "Paris", user);
		taskService.deleteTask(task);
		assertNull(taskService.find(task));
	}
}
