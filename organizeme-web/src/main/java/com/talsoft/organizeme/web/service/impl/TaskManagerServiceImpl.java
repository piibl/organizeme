package com.talsoft.organizeme.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.service.AbstractCrudService;
import com.talsoft.organizeme.core.service.task.impl.repository.TaskRepository;
import com.talsoft.organizeme.web.dto.impl.TaskDTO;
import com.talsoft.organizeme.web.service.TaskManagerService;

@Named
public class TaskManagerServiceImpl extends AbstractCrudService<Task, Long> implements TaskManagerService {

	// formateur de date
	// TODO à externaliser
	private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");

	@Inject
	private TaskRepository taskRepository;

	@Override
	public List<Task> findByOwnerNonArchived(EndUser owner) {
		return taskRepository.findByOwnerAndStartDateGreaterThanOrderByStartDateAsc(owner, new DateTime());
	}

	@Override
	protected JpaRepository<Task, Long> getRepository() {
		return taskRepository;
	}

	@Override
	public List<Task> findNotArchivedByOwner(EndUser owner) {
		// recherche des notes actives, triées par date de début
		return taskRepository.findByOwnerAndEndDateGreaterThanOrderByStartDateAsc(owner, new DateTime());
	}

	@Override
	public Task createFromDTO(TaskDTO entityDTO, EndUser owner) {
		return taskRepository.save(new Task(entityDTO.getTitle(), entityDTO.getGoal(), entityDTO.getPriority(), entityDTO.getLocalization(), owner,
				formatter.parseDateTime(entityDTO.getStartDate()), formatter.parseDateTime(entityDTO.getEndDate())));
	}

	@Override
	public Task saveFromDTO(TaskDTO entityDTO, Long id) {
		Task task = taskRepository.findOne(id);
		if (task != null) {
			// transfert
			// TODO check des valeurs plus implé partielle
			task.setTitle(entityDTO.getTitle());
			task.setGoal(entityDTO.getGoal());
			task.setPriority(entityDTO.getPriority());
			task.setLocalization(entityDTO.getLocalization());
			task.setStartDate(formatter.parseDateTime(entityDTO.getStartDate()));
			task.setEndDate(formatter.parseDateTime(entityDTO.getEndDate()));
			return taskRepository.save(task);
		}
		// TODO cas erreur !!!
		return null;
	}

}
