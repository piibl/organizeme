package com.talsoft.organizeme.web.link.assembler;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.util.Assert;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.web.controller.domain.task.TaskController;
import com.talsoft.organizeme.web.link.AbstractResourceAssembler;
import com.talsoft.organizeme.web.link.ControllerLinkBuilderFactory;
import com.talsoft.organizeme.web.reference.path.DomainPath;
import com.talsoft.organizeme.web.reference.relation.GlobalRelation;
import com.talsoft.organizeme.web.reference.relation.TaskRelation;

/**
 * Représente les ressources associés à un tache existant <br/>
 * Il s'agit d'une collection des liens permettant d'adresser ces ressources
 */
@Named
public class TaskResourceAssembler extends AbstractResourceAssembler<Task, Resource<Task>> {

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	public TaskResourceAssembler() {

	}

	@Override
	public Resource<Task> toResource(Task task) {
		Assert.notNull(task);
		// Adressage d'un tache
		Link selfLink = linkBuilderFactory.linkTo(TaskController.class).slash(task.getId()).withRel(GlobalRelation.SELF.getName());
		// Adressage de tous les notes
		Link notesLink = linkBuilderFactory.linkTo(TaskController.class).withRel(TaskRelation.NOTES.getName());
		// Adressage pour la suppression d'un tache
		Link deleteLink = linkBuilderFactory.linkTo(TaskController.class).slash(task.getId()).slash(DomainPath.DELETE.getPath())
				.withRel(GlobalRelation.DELETE.getName());
		// Adressage pour la modification d'un tache
		Link editLink = linkBuilderFactory.linkTo(TaskController.class).slash(task.getId()).slash(DomainPath.EDIT.getPath())
				.withRel(GlobalRelation.EDIT.getName());
		return new Resource<Task>(task, selfLink, notesLink, deleteLink, editLink);
	}
}
