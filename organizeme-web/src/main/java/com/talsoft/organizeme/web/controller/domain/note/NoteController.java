package com.talsoft.organizeme.web.controller.domain.note;

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

import com.talsoft.organizeme.core.domain.note.Note;
import com.talsoft.organizeme.core.domain.user.EndUser;
import com.talsoft.organizeme.core.service.user.UserResearchService;
import com.talsoft.organizeme.web.controller.domain.AbstractDomainController;
import com.talsoft.organizeme.web.dto.impl.NoteDTO;
import com.talsoft.organizeme.web.link.BatchResourceAssembler;
import com.talsoft.organizeme.web.link.assembler.NoteResourceAssembler;
import com.talsoft.organizeme.web.reference.link.LinkName;
import com.talsoft.organizeme.web.reference.path.DomainPath;
import com.talsoft.organizeme.web.service.NoteManagerService;
import com.talsoft.organizeme.web.service.OwnedDomainObjectService;

@Controller
@RequestMapping("/dashboard/notes")
public class NoteController extends AbstractDomainController<Note, Long, EndUser, NoteDTO> {

	private String mainView = "domain/notes";
	private String partialView = "domain/display/tables :: notes-table";
	private String detailsView = "domain/display/details :: display-note-details";
	private String entitiesAttributeName = "notes";
	private String singleEntityAttributeName = "note";
	private String addForm = "domain/forms/add-forms :: add-note-form";
	private String editForm = "domain/forms/edit-forms :: edit-note-form";

	@Inject
	private NoteManagerService noteManagerService;
	@Inject
	private UserResearchService userResearchService;
	@Inject
	private NoteResourceAssembler noteResourceAssembler;

	@ModelAttribute("redirectLink")
	public Link getRedirectLink() {
		// lien de redirection après supression objet ou ajout = méthode de rendu partiel
		return linkBuilderFactory.linkTo(NoteController.class).slash(DomainPath.PARTIAL.getPath()).withSelfRel();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAllByOwner(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("login", user.getUsername());
		model.addAttribute(LinkName.NEW_NOTE.getName(), linkBuilderFactory.linkTo(NoteController.class).slash(DomainPath.NEW.getPath()).withSelfRel());
		// Ajout lien pour création nouvelle tache
		model.addAttribute(LinkName.NEW_NOTE.getName(), linkBuilderFactory.linkTo(NoteController.class).slash(DomainPath.NEW.getPath()).withSelfRel());
		// Ajout de lien de redirection après supression objet ou ajout = méthode de rendu partiel
		model.addAttribute(LinkName.REDIRECT.getName(), linkBuilderFactory.linkTo(NoteController.class).slash(DomainPath.PARTIAL.getPath())
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
		model.addAttribute(LinkName.REDIRECT.getName(), linkBuilderFactory.linkTo(NoteController.class).slash(DomainPath.PARTIAL.getPath())
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
	protected String addNew(@RequestBody NoteDTO entityDTO, Model model, @AuthenticationPrincipal User user) {
		getDomainService().createFromDTO(entityDTO, userResearchService.findByLogin(user.getUsername()));
		// enrichir la vue partielle
		return getAllByOwnerPartialRender(model, user);
	}

	/**
	 * Requete AJAX pour update d'une entité
	 * 
	 * @param entityDTO
	 * @param noteId
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/{noteId}/edit", method = RequestMethod.POST)
	protected String update(@RequestBody NoteDTO entityDTO, @PathVariable Long noteId, Model model, @AuthenticationPrincipal User user) {
		getDomainService().saveFromDTO(entityDTO, noteId);
		// enrichir la vue partielle
		return getAllByOwnerPartialRender(model, user);
	}

	// Accesseurs pour le controlleur abstrait
	@Override
	protected OwnedDomainObjectService<Note, Long, EndUser, NoteDTO> getDomainService() {
		return noteManagerService;
	}

	@Override
	protected BatchResourceAssembler<Note, Resource<Note>> getResourceAssembler() {
		return noteResourceAssembler;
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
