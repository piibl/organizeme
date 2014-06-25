package com.talsoft.organizeme.web.controller.domain.task;

import javax.inject.Inject;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talsoft.organizeme.core.domain.task.Task;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.service.user.UserResearchService;
import com.talsoft.organizeme.web.controller.domain.AbstractDomainController;
import com.talsoft.organizeme.web.dto.impl.TaskDTO;
import com.talsoft.organizeme.web.link.BatchResourceAssembler;
import com.talsoft.organizeme.web.link.assembler.TaskResourceAssembler;
import com.talsoft.organizeme.web.reference.link.LinkName;
import com.talsoft.organizeme.web.reference.path.DomainPath;
import com.talsoft.organizeme.web.service.OwnedDomainObjectService;
import com.talsoft.organizeme.web.service.TaskManagerService;

@Controller
@RequestMapping("/dashboard/tasks")
public class TaskController extends AbstractDomainController<Task, Long, EndUser, TaskDTO> {

	private String mainView = "domain/tasks";
	private String partialView = "domain/display/tables :: tasks-table";
	private String detailsView = "domain/details/tasks :: display-details";
	private String entitiesAttributeName = "tasks";
	private String singleEntityAttributeName = "task";
	private String addForm = "domain/forms/add-forms :: add-task-form";
	private String editForm = "domain/forms/edit-forms :: edit-task-form";

	@Inject
	private TaskManagerService taskManagerService;
	@Inject
	private UserResearchService userResearchService;
	@Inject
	private TaskResourceAssembler taskResourceAssembler;

	@ModelAttribute("redirectLink")
	public Link getRedirectLink() {
		// lien de redirection après supression objet ou ajout = méthode de rendu partiel
		return linkBuilderFactory.linkTo(TaskController.class).slash(DomainPath.PARTIAL.getPath()).withSelfRel();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAllByOwner(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("login", user.getUsername());
		model.addAttribute(LinkName.NEW_TASK.getName(), linkBuilderFactory.linkTo(TaskController.class).slash(DomainPath.NEW.getPath()).withSelfRel());
		// Ajout lien pour création nouvelle tache
		model.addAttribute(LinkName.NEW_TASK.getName(), linkBuilderFactory.linkTo(TaskController.class).slash(DomainPath.NEW.getPath()).withSelfRel());
		// Ajout de lien de redirection après supression objet ou ajout = méthode de rendu partiel
		model.addAttribute(LinkName.REDIRECT.getName(), linkBuilderFactory.linkTo(TaskController.class).slash(DomainPath.PARTIAL.getPath())
				.withSelfRel());
		// recherche de l'utilisateur et rendu
		return super.getByOwner(model, userResearchService.findByLogin(user.getUsername()));
	}

	/**
	 * Méthode de rendu partiel, c'est-à dire que les données brutes <br/>
	 * TODO ne traiter que les appels AJAX, ignorer les autres
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/partial", method = RequestMethod.GET)
	public String getAllByOwnerPartialRender(Model model, @AuthenticationPrincipal User user) {
		// Ajout de lien de redirection après supression objet ou ajout = méthode de rendu partiel
		model.addAttribute(LinkName.REDIRECT.getName(), linkBuilderFactory.linkTo(TaskController.class).slash(DomainPath.PARTIAL.getPath())
				.withSelfRel());
		return super.getByOwnerPartialRender(model, userResearchService.findByLogin(user.getUsername()));
	}

	/**
	 * Requete ajax pour création entité
	 * 
	 * @param entityDTO
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected String addNew(@RequestBody TaskDTO entityDTO, Model model, @AuthenticationPrincipal User user) {
		getDomainService().createFromDTO(entityDTO, userResearchService.findByLogin(user.getUsername()));
		// enrichir la vue partielle
		return getAllByOwnerPartialRender(model, user);
	}

	/**
	 * Requete AJAX pour update d'une entité
	 * 
	 * @param entityDTO
	 * @param taskId
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/{taskId}/edit", method = RequestMethod.POST)
	protected String update(@RequestBody TaskDTO entityDTO, @PathVariable Long taskId, Model model, @AuthenticationPrincipal User user) {
		getDomainService().saveFromDTO(entityDTO, taskId);
		// enrichir la vue partielle
		return getAllByOwnerPartialRender(model, user);
	}

	// Accesseurs pour le controlleur abstrait
	@Override
	protected OwnedDomainObjectService<Task, Long, EndUser, TaskDTO> getDomainService() {
		return taskManagerService;
	}

	@Override
	protected BatchResourceAssembler<Task, Resource<Task>> getResourceAssembler() {
		return taskResourceAssembler;
	}

	@Override
	protected String getMainView() {
		return mainView;
	}

	@Override
	protected String getPartialView() {
		return partialView;
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
