package com.talsoft.organizeme.web.link.assembler;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.util.Assert;

import com.talsoft.organizeme.core.domain.note.Note;
import com.talsoft.organizeme.web.controller.domain.note.NoteController;
import com.talsoft.organizeme.web.link.AbstractResourceAssembler;
import com.talsoft.organizeme.web.link.ControllerLinkBuilderFactory;
import com.talsoft.organizeme.web.reference.path.DomainPath;
import com.talsoft.organizeme.web.reference.relation.GlobalRelation;

/**
 * Représente les ressources associés à un note existant <br/>
 * Il s'agit d'une collection des liens permettant d'adresser ces ressources
 */
@Named
public class NoteResourceAssembler extends AbstractResourceAssembler<Note, Resource<Note>> {

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	public NoteResourceAssembler() {

	}

	@Override
	public Resource<Note> toResource(Note note) {
		Assert.notNull(note);
		// Adressage d'un note
		Link selfLink = linkBuilderFactory.linkTo(NoteController.class).slash(note.getId()).withRel(GlobalRelation.SELF.getName());
		// Adressage de tous les notes
		// Link notesLink = linkBuilderFactory.linkTo(NoteController.class).withRel(NoteRelation.NOTES.getName());
		// Adressage pour la suppression d'un note
		Link deleteLink = linkBuilderFactory.linkTo(NoteController.class).slash(note.getId()).slash(DomainPath.DELETE.getPath())
				.withRel(GlobalRelation.DELETE.getName());
		// Adressage pour la modification d'un note
		Link editLink = linkBuilderFactory.linkTo(NoteController.class).slash(note.getId()).slash(DomainPath.EDIT.getPath())
				.withRel(GlobalRelation.EDIT.getName());
		return new Resource<Note>(note, selfLink, deleteLink, editLink);
	}
}
