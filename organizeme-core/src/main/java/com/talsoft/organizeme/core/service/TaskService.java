package com.talsoft.organizeme.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.talsoft.organizeme.core.domain.User;
import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.service.repository.task.TaskRepository;

@Named
public class TaskService {

	@Inject
	private TaskRepository taskRepository;

	/**
	 * Crée une tâche
	 * 
	 * @param goal
	 *            : but de la tâche
	 * @param priority
	 *            : priorité de la tâche
	 * @param localization
	 *            : localisation de la tâche
	 * @param periodDay
	 * @param periodMonth
	 * @param owner
	 *            : propriétaire de la tâche
	 * @return : tâche
	 */
	public Task createTask(String goal, String priority, String localization, User owner) {
		Task task = new Task(goal, priority, localization, owner);
		return taskRepository.save(task);
	}

	public Task find(Task task) {
		return taskRepository.findOne(task.getId());
	}

	/**
	 * Supprime une tâche
	 * 
	 * @param taskToDelete
	 *            : tâche à supprimer
	 */
	public void deleteTask(Task taskToDelete) {
		taskRepository.delete(taskToDelete);
	}

}
