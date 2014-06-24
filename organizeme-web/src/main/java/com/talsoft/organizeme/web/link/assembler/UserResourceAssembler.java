package com.talsoft.organizeme.web.link.assembler;

import javax.inject.Named;

import org.springframework.hateoas.Resource;

import com.talsoft.organizeme.core.domain.user.Administrator;
import com.talsoft.organizeme.web.link.AbstractResourceAssembler;

/**
 * Représente les ressources associés à un administrateur existant <br/>
 * Il s'agit d'une collection des liens permettant d'adresser ces ressources
 */
@Named
public class UserResourceAssembler extends AbstractResourceAssembler<Administrator, Resource<Administrator>> {

	// @Inject
	// private ControllerLinkBuilderFactory linkBuilderFactory;

	public UserResourceAssembler() {

	}

	@Override
	public Resource<Administrator> toResource(Administrator administrator) {
		// Assert.notNull(administrator);
		// // Adressage d'un administrateur
		// Link selfLink = linkBuilderFactory.linkTo(AdministratorController.class).slash(administrator.getId()).withRel(Relation.SELF.getName());
		// // Adressage de tous les administrateurs
		// Link studentsLink = linkBuilderFactory.linkTo(AdministratorController.class).withRel(Relation.ADMINISTRATORS.getName());
		// // Adressage pour la suppression d'un administrateur
		// Link deleteLink =
		// linkBuilderFactory.linkTo(AdministratorController.class).slash(administrator.getId()).slash(PathFragment.DELETE.getPath())
		// .withRel(Relation.DELETE.getName());
		// // Adressage pour la modification d'un administrateur
		// Link editLink = linkBuilderFactory.linkTo(AdministratorController.class).slash(administrator.getId()).slash(PathFragment.EDIT.getPath())
		// .withRel(Relation.EDIT.getName());
		return null;
	}

}
