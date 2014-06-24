package com.talsoft.organizeme.web.controller.domain.task;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.User;
import com.talsoft.organizeme.web.controller.domain.AbstractDomainController;
import com.talsoft.organizeme.web.link.BatchResourceAssembler;
import com.talsoft.organizeme.web.link.assembler.TaskResourceAssembler;
import com.talsoft.organizeme.web.service.OwnedDomainObjectService;
import com.talsoft.organizeme.web.service.TaskManagerService;

@Controller
@RequestMapping("/tasks")
public class TaskController extends AbstractDomainController<Task, Long, User> {

	private String mainView = "tasks";
	private String detailsView = "details/tasks :: display-details";
	private String entitiesAttributeName = "tasks";
	private String singleEntityAttributeName = "task";
	private String addForm = "forms/add-forms :: add-task-form";
	private String editForm = "forms/edit-forms :: edit-task-form";

	@Inject
	private TaskManagerService taskManagerService;
	@Inject
	private TaskResourceAssembler taskResourceAssembler;

	@Override
	protected OwnedDomainObjectService<Task, Long, User> getDomainService() {
		return taskManagerService;
	}

	@Override
	protected BatchResourceAssembler<Task, Resource<Task>> getResourceAssembler() {
		return taskResourceAssembler;
	}

	@Override
	protected String getBaseView() {
		return mainView;
	}

	@Override
	protected String getDetailsView() {
		return detailsView;
	}

	@Override
	protected String getEntitiesAtributeName() {
		return entitiesAttributeName;
	}

	@Override
	protected String getSingleEntityAtributeName() {
		return singleEntityAttributeName;
	}

	@Override
	protected String getAddFormPath() {
		return addForm;
	}

	@Override
	protected String getEditFormPath() {
		return editForm;
	}

}
