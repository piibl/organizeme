package com.talsoft.organizeme.web.controller.domain;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talsoft.organizeme.web.link.BatchResourceAssembler;
import com.talsoft.organizeme.web.link.ControllerLinkBuilderFactory;
import com.talsoft.organizeme.web.service.OwnedDomainObjectService;

public abstract class AbstractDomainController<T, X extends Serializable, Y> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractDomainController.class);

	/**
	 * Constructeur de liens
	 */
	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;

	/**
	 * Retourne le service CRUD du type de l'entité cible
	 */
	protected abstract OwnedDomainObjectService<T, X, Y> getDomainService();

	/**
	 * Retourne l'assembleur de ressources associé au type de l'entité cible
	 * 
	 * @return
	 */
	protected abstract BatchResourceAssembler<T, Resource<T>> getResourceAssembler();

	/**
	 * Retourne le path de la vue principale du controller concret
	 * 
	 * @return
	 */
	protected abstract String getBaseView();

	/**
	 * Retourne le path de la vue détaillée d'une instance
	 */
	protected abstract String getDetailsView();

	/**
	 * Retourne le nom de l'attribut associé à la liste de toutes les entités du type cible
	 * 
	 * @return
	 */
	protected abstract String getEntitiesAtributeName();

	/**
	 * Retourne le nom de l'attribut associé à une seule entité du type cible
	 * 
	 * @return
	 */
	protected abstract String getSingleEntityAtributeName();

	/**
	 * Retourne le path du formulaire d'ajout
	 * 
	 * @return
	 */
	protected abstract String getAddFormPath();

	/**
	 * Retourne le path du formulaire de modification
	 * 
	 * @return
	 */
	protected abstract String getEditFormPath();

	/**
	 * Vue globale, affiche tous les éléments du type de l'entité
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model) {
		List<T> entities = getDomainService().findAll();
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<T>> entitiesResources = getResourceAssembler().toResource(entities);
		model.addAttribute(getEntitiesAtributeName(), entitiesResources);
		return getBaseView();
	}

	/**
	 * Retourne le formulaire de saisie d'une nouvelle instance <br/>
	 * Si recquiert l'utilisation du profil de l'utilisateur courant, surcharger la méthode dans la classe concrète.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getAddForm(Model model, @AuthenticationPrincipal User user) {
		return getAddFormPath();
	}

	/**
	 * Demande de création
	 * 
	 * @param entity
	 * @return
	 */
	// @RequestMapping(method = RequestMethod.POST)
	// public String insertNewJSON(@RequestBody Y entityDTO, Model model) {
	// getCrudService().createFromDTO(entityDTO);
	// // Alimenter le modèle avec la liste mise à jour
	// return getAll(model);
	// }

	/**
	 * Retourne l'entité correspondant à l'id cible
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
	public String getEntity(@PathVariable("entityId") X id, Model model) {
		// TODO Quick'n'dirty, j'aime !
		Resource<T> resource = getResourceAssembler().toResource(getDomainService().find(id));
		model.addAttribute(getSingleEntityAtributeName(), resource);
		logger.info("getDetails calls : [" + getDetailsView() + "]");
		return getDetailsView();
	}

	/**
	 * Retourne le formulaire d'édition associé à l'instance
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{entityId}/edit", method = RequestMethod.GET)
	public String editEntity(@PathVariable("entityId") X id, Model model) {
		Resource<T> resource = getResourceAssembler().toResource(getDomainService().find(id));
		// TODO externalisation / personnalisation selon entités
		// Message de suppression
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		model.addAttribute(getSingleEntityAtributeName(), resource);
		return getEditFormPath();
	}

	/**
	 * Demande de modification, format JSON
	 * 
	 * @param entity
	 * @return
	 */
	// @RequestMapping(value = "/{entityId}/edit", method = RequestMethod.POST)
	// public String updateJSON(@RequestBody Y entityDTO, @PathVariable("entityId") X id, Model model) {
	// getCrudService().saveFromDTO(entityDTO, id);
	// // Alimenter le modèle avec la liste mise à jour
	// return getAll(model);
	// }

	/**
	 * Supprime l'entité correspondant à l'id passé en paramètre
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{entityId}/delete", method = RequestMethod.GET)
	public String deleteEntity(@PathVariable("entityId") X id, Model model) {
		T entityTodelete = getDomainService().find(id);
		getDomainService().delete(entityTodelete);
		// TODO externalisation / personnalisation selon entités
		// Message de suppression
		List<T> entities = getDomainService().findAll();
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<T>> entitiesResources = getResourceAssembler().toResource(entities);
		model.addAttribute(getEntitiesAtributeName(), entitiesResources);
		return getAll(model);
	}

}
